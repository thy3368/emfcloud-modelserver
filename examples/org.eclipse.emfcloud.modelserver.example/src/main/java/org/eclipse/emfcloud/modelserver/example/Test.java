package org.eclipse.emfcloud.modelserver.example;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import java.io.IOException;
import java.util.List;

public class Test {

    void abc() throws IOException {


        EPackage myPackage = EcoreFactory.eINSTANCE.createEPackage();
        myPackage.setName("MyPackage");
        myPackage.setNsURI("***");
        myPackage.setNsPrefix("my");

        EClass myClass = EcoreFactory.eINSTANCE.createEClass();
        myClass.setName("MyClass");

        myClass.getEAnnotations().add(null);

        EAttribute myAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        myAttribute.setName("myAttribute");
        myAttribute.setEType(EcorePackage.eINSTANCE.getEString());

        myAttribute.getEAnnotations().add(null);


        EOperation myOperation = EcoreFactory.eINSTANCE.createEOperation();

        EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();
        annotation.eSet(null,null);



        EOperation operation = EcoreFactory.eINSTANCE.createEOperation();
        operation.getEAnnotations().add(annotation);


        myClass.getEAnnotations().add(annotation);

        myOperation.setName("myOperation");

        myClass.getEStructuralFeatures().add(myAttribute);

        myClass.getEAllOperations().add(myOperation);


        myPackage.getEClassifiers().add(myClass);

        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.createResource(URI.createURI("myPackage.xmi"));
        resource.getContents().add(myPackage);
        resource.save(null);

    }

    void abcd() {
        EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
        EcorePackage ecorePackage = EcorePackage.eINSTANCE;
//创建一Company类
        EClass companyClass = ecoreFactory.createEClass();
        companyClass.setName("Company");
//创建公司名
        EAttribute companyName = ecoreFactory.createEAttribute();
        companyName.setName("name");
        companyName.setEType(ecorePackage.getEString());
        companyClass.getEStructuralFeatures().add(companyName);
//创建一Employee类
        EClass employeeClass = ecoreFactory.createEClass();
        employeeClass.setName("Employee");
//在Employee类上添加一个名字属性
        EAttribute employeeName = ecoreFactory.createEAttribute();
        employeeName.setName("name");
        employeeName.setEType(ecorePackage.getEString());
        employeeClass.getEStructuralFeatures().add(employeeName);
//创建一Department类
        EClass departmentClass = ecoreFactory.createEClass();
        departmentClass.setName("Department");
//添加department标志数字
        EAttribute departmentNumber = ecoreFactory.createEAttribute();
        departmentNumber.setName("number");
        departmentNumber.setEType(ecorePackage.getEInt());
        departmentClass.getEStructuralFeatures().add(departmentNumber);
//department类能够包含到一个或多个employee的参考
        EReference departmentEmployees = ecoreFactory.createEReference();
        departmentEmployees.setName("employees");
        departmentEmployees.setEType(employeeClass);
//指定它可能是一个或多个employee
        departmentEmployees.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
        departmentEmployees.setContainment(true);
        departmentClass.getEStructuralFeatures().add(departmentEmployees);
//company能够包含到一个或多个departments的参考
        EReference companyDepartments = ecoreFactory.createEReference();
        companyDepartments.setName("department");
        companyDepartments.setEType(departmentClass);
        companyDepartments.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
        companyDepartments.setContainment(true);
        companyClass.getEStructuralFeatures().add(companyDepartments);
//创建一个包-描述company
        EPackage companyPackage = ecoreFactory.createEPackage();
        companyPackage.setName("company");
        companyPackage.setNsPrefix("company");
        companyPackage.setNsURI("http:///com.example.company.ecore");
        companyPackage.getEClassifiers().add(employeeClass);
        companyPackage.getEClassifiers().add(departmentClass);
        companyPackage.getEClassifiers().add(companyClass);
//得到company工厂
        EFactory companyFactory = companyPackage.getEFactoryInstance();
//使用工厂来创建company类的实例并且
//设置company名字
        EObject company = companyFactory.create(companyClass);
        company.eSet(companyName, "MyCompany");
//创建一个employee类的实例
        EObject employee = companyFactory.create(employeeClass);
//使用反射API初始化employee的名字
        employee.eSet(employeeName, "John");
//创建一个department类的实例
        EObject department = companyFactory.create(departmentClass);
        department.eSet(departmentNumber, Integer.valueOf(123));
//添加"John"到department
        ((List) department.eGet(departmentEmployees)).add(employee);
//添加department到company
        ((List) company.eGet(companyDepartments)).add(department);
    }
}
