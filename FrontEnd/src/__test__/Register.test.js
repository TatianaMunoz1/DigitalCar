import React, {useState} from "react";
import '@testing-library/jest-dom'
import { shallow, mount } from "enzyme";
import { createMemoryHistory } from "history";
import { Router } from "react-router-dom";
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import Header from '../components/Header/Header.js'
import Register from '../pages/Register/Register.js';


test("renders without crashing", () => {
    shallow(<Header />);
  });

describe ('<Register />',() => {
   
    let wrapper;
    const setState = jest.fn();
    const useStateSpy = jest.spyOn(React, "useState")
    useStateSpy.mockImplementation((init) => [init, setState]);
    
    beforeEach(() => {
        wrapper =mount(shallow(<Register />).get(0))
    });
    
    afterEach(() => {
        jest.clearAllMocks();
    });
    const componenteUser = [{
        id: "testname",
        name :"testname",
        placeholder: "Andrea",
        type : "text",
        value : "Andrea"

    },
    {
        id: "testApellido",
        name :"testApellido",
        placeholder: "Martinez",
        type : "text",
        value : "Marinez"

    },
    {
        id: "testemail",
        name :"testemail",
        placeholder: "correo@correo.com",
        type : "email",
        value : "andreamartinez@correo.com"
      },
     {
        id: "testpasword",
        name :"testpasword",
        placeholder: "digitalcar123",
        type : "password",
        value : "andream123"
      }]

      const history = createMemoryHistory();
      
        
       
        
    test("Should capture title correctly onChange", () => {
        const title = wrapper.find("input1").at(0);
        title.instance().value = "Nombre";
        title.simulate("change");
        expect(setState).toHaveBeenCalledWith("Andrea");
            });
        
    
        
    test("Should capture content correctly onChange", () => {
        const content = wrapper.find("email").at(1);
        content.instance().value = "correo@correo.com";
        content.simulate("change");
        expect(setState).toHaveBeenCalledWith("andreamartinez@correo.com");
            });
        
    test("must be exist", () => {
        const wrapper = mount(
            <Router history={history}>
                <Register />
            </Router> );

         expect(wrapper.text().includes("Inicia sesión")).toBeTruthy();
        expect(wrapper.text().includes("Ambas contraseñas deben ser iguales.")).toBeTruthy();
        expect(wrapper.text().includes("¿Ya tienes una cuenta?")).toBeTruthy();
        
      });

     /* test('name check',()=>{
        const onClickSpy = jest.fn();
        const wrapper = shallow(<Register component ={componenteUser} handleChange={onClickSpy}/>);
        expect(wrapper).toMatchSnapshot();
        wrapper.find('Inputs [type="text"]').simulate('change', {target: {name: 'Nombre', value: "Andrea"}});
        expect(onClickSpy).toHaveBeenCalled();
        expect(wrapper.state('Nombre')).toEqual('Andrea');
    })
      
    test('Apellido check',()=>{
        const wrapper = shallow(<Register component ={componenteUser}/>);
        wrapper.find('Inputs [type="text"]').simulate('change', {target: {name: 'lastname', value: "Martinez"}});
        expect(wrapper.state('name')).toEqual('Andrea');
    });
        
      test('email check',()=>{
        const wrapper = shallow(<Register component ={componenteUser}/>);
        wrapper.find('Inputs [type="email"]').simulate('change', {target: {name: 'email', value: "andreamartinez@correo.com"}});
        expect(wrapper.state('email')).toEqual("andreamartinez@correo.com");
    });
    test('password check',()=>{
        const wrapper = shallow(<Register component ={componenteUser}/>);
        wrapper.find('Inputs [type="password"]').simulate('change', {target: {name: 'password', value: "andream123"}});
        expect(wrapper.state('password')).toEqual('andream123');
    })*/
    

})