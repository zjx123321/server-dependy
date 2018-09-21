//package com.example.flow.demo;
//
//import com.example.flow.demo.entity.node.Module;
//import com.example.flow.demo.entity.node.Service;
//import com.example.flow.demo.entity.relation.CallModule;
//import com.example.flow.demo.repositories.CallModuleRepository;
//import com.example.flow.demo.repositories.CallServiceRepository;
//import com.example.flow.demo.repositories.ModuleRepository;
//import com.example.flow.demo.repositories.ServiceRepository;
//import com.google.common.collect.Sets;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Set;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ComponentScan("com.example.flow.demo")
//public class DemoApplicationTests {
//
//	@Test
//	public void contextLoads() {
//	}
//
//	@Autowired
//	private ServiceRepository serviceRepository;
//
//	@Autowired
//	private ModuleRepository moduleRepository;
//
//	@Autowired
//	private CallServiceRepository callServiceRepository;
//
//	@Autowired
//	private CallModuleRepository callModuleRepository;
//
//	@Test
//	public void testCreat() {
//		Module faceHead = new Module();
//		faceHead.setName("faceHead");
//		Set<Service> set = Sets.newHashSet();
//		Service s = new Service();
//		s.setName("/base/1");
//		s.setModule(faceHead);
//		set.add(s);
//		faceHead.setServices(set);
//		moduleRepository.save(faceHead);
//
//		Module baseMatch = new Module();
//		baseMatch.setName("baseMatch");
//		Set<Service> set2 = Sets.newHashSet();
//		Service s2 = new Service();
//		s2.setName("/baseinfo");
//		s2.setModule(baseMatch);
//		set2.add(s2);
//		baseMatch.setServices(set2);
//		moduleRepository.save(baseMatch);
//
//		CallModule face2Base = new CallModule();
//		face2Base.setStart(faceHead);
//		face2Base.setEnd(baseMatch);
//		face2Base.setRelation(faceHead.getId() + "-" + baseMatch.getId());
//		callModuleRepository.save(face2Base);
//	}
//
//	@Test
//	public void testQuery() {
//		Module module = moduleRepository.findByName("faceHead");
////		Module module = moduleRepository.findById(m.getId()).get();
//		Service s = new Service();
//		s.setName("/base/3");
//		s.setModule(module);
//		module.getServices().add(s);
//		moduleRepository.save(module);
//		System.out.println(module.getId());
//	}
//
//	@Test
//	public void testRelation() {
//		Module module = moduleRepository.findByName("faceHead");
//
//		Module gateway = new Module();
//		gateway.setName("gateway");
//		moduleRepository.save(gateway);
//
//		CallModule gway2Face = new CallModule();
//		gway2Face.setStart(gateway);
//		gway2Face.setEnd(module);
//		gway2Face.setRelation(gateway.getId() + "-" + module.getId());
//		callModuleRepository.save(gway2Face);
//		System.out.println(gway2Face.getId());
//	}
//
//	@Test
//	public void testRelation2() {
//		Module faceHead = moduleRepository.findByName("faceHead");
//
//		Module gateway = moduleRepository.findByName("gateway");
//
//		Module faceList = new Module();
//		faceList.setName("faceList");
//		moduleRepository.save(faceList);
//
//		CallModule gway2List = new CallModule();
//		gway2List.setStart(gateway);
//		gway2List.setEnd(faceList);
//		gway2List.setRelation(gateway.getId() + "-" + faceList.getId());
//		callModuleRepository.save(gway2List);
//
//		CallModule list2Head = new CallModule();
//		list2Head.setStart(faceList);
//		list2Head.setEnd(faceHead);
//		list2Head.setRelation(faceList.getId() + "-" + faceHead.getId());
//		callModuleRepository.save(list2Head);
//	}
//
//
//	@Test
//	public void testRelation3() {
//		Module faceHead = moduleRepository.findByName("faceHead");
//
//		Module baseMatch = moduleRepository.findByName("baseMatch");
//
////		Long relation = callModuleRepository.findIdByModules(faceHead, baseMatch);
////		CallModule callModule = callModuleRepository.findById(relation).get();
////		if(relation != null) {
//////			System.out.println(relation.getId());
////		}
////
////		CallModule list2Head = new CallModule();
////		list2Head.setStart(gateway);
////		list2Head.setEnd(faceList);
////		list2Head.setRelation(gateway.getId() + "-" + faceList.getId());
////		callModuleRepository.save(list2Head);
//	}
//
//	@Test
//	public void testUpdate() {
//
//	}
//
//	@Test
//	public void testDelete() {
//
//	}
//
//}
