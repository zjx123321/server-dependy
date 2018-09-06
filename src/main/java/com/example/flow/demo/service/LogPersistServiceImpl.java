package com.example.flow.demo.service;

import com.example.flow.demo.dto.LogDto;
import com.example.flow.demo.entity.node.Module;
import com.example.flow.demo.entity.node.Service;
import com.example.flow.demo.entity.relation.CallModule;
import com.example.flow.demo.entity.relation.CallService;
import com.example.flow.demo.repositories.CallModuleRepository;
import com.example.flow.demo.repositories.CallServiceRepository;
import com.example.flow.demo.repositories.ModuleRepository;
import com.example.flow.demo.repositories.ServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by zhengjiexiang on 2018/9/5
 */
@org.springframework.stereotype.Service
public class LogPersistServiceImpl implements LogPersistService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ServiceRepository serviceRepository;
    @Resource
    private ModuleRepository moduleRepository;
    @Resource
    private CallServiceRepository callServiceRepository;
    @Resource
    private CallModuleRepository callModuleRepository;

    @Override
    public void persist(LogDto logDto) {

        try {

            long start = System.currentTimeMillis();

            while (logDto.getParent() != null) {

                LogDto parent = logDto.getParent();
                // 持久当前节点
                persistNode(logDto);
                // 持久父节点
                persistNode(parent);
                // 持久化关系
                persistRelation(logDto, parent);

                // 防止死循环，5分钟
                if (System.currentTimeMillis() - start > 1000 * 5 * 60) {
                    logger.error("可能出现死循环" + logDto);
                    break;
                }
                // 重置
                logDto = parent;
            }
        }catch (Exception e) {
            logger.error("持久化报错:" + logDto, e);
        }

    }

//    @Transactional
    public void persistRelation(LogDto current, LogDto parent) {
        Long idByModules = callModuleRepository.findIdByModules(parent.getModule(), current.getModule());
        Long idByServices = callServiceRepository.findIdByServices(parent.getService(), parent.getModule(), current.getService(), current.getModule());
        if(idByModules == null) {
            CallModule relation = new CallModule();
            Module start = moduleRepository.findByName(parent.getModule());
            Module end = moduleRepository.findByName(current.getModule());
            relation.setStart(start);
            relation.setEnd(end);
            relation.setRelation(start.getId() + "-" + end.getId());
            callModuleRepository.save(relation);
        }

        if(idByServices == null) {
            CallService relation = new CallService();
            Service start = serviceRepository.findByNames(parent.getService(), parent.getModule());
            Service end = serviceRepository.findByNames(current.getService(), current.getModule());
            relation.setStart(start);
            relation.setEnd(end);
            relation.setRelation(start.getId() + "-" + end.getId());
            relation.setTime(current.getCostTime());
            callServiceRepository.save(relation);
        }
    }

//    @Transactional
    public void persistNode(LogDto logDto) {
        //判断module及service是否存在，不存在则创建
        Module module = logDto.buildModule();
        Service service = module.getServices().stream().findFirst().get();

        Service beforeService = serviceRepository.findByNames(service.getName(), module.getName());
        Module beforeModule = moduleRepository.findByName(module.getName());

        // service存在的话，module必定存在
        if (beforeService == null) {
            if(beforeModule == null) {
                serviceRepository.save(service);
            }
            else{

                service.setModule(beforeModule);
                serviceRepository.save(service);

//                // service第一次创建，则需要维护到 module中去
//                beforeModule.getServices().add(service);
//                moduleRepository.save(beforeModule);

            }
        }
    }

}
