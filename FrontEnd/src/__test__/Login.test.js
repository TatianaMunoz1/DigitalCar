import React from "react";
import '@testing-library/jest-dom'
import { shallow } from "enzyme";
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import Login from "../pages/Login/Login.js"


describe ('<Login />',() => {
    /*const componenteInput =
    ({ email, password, onChange }) => (
        <div>
          <input name="email" value={email} onChange={onChange} />
          <input name="password" value={password} onChange={onChange} />
        </div>
      );
    componenteInput.propTypes={
        email: PropTypes.email.isRequired,
        password: PropTypes.password.isRequired,
        onChange: PropTypes.func.isRequired,
    };
    
    const testState = { email:"correo@correo.com" , password: "digitalcar123" };
        const wrapper = shallow((
  <Login
    email={testState.email}
    password={testState.password}
    onChange={(e) => {
      testState[e.target.name] = e.target.value;
    }}
  />
  ));
expect(wrapper.find('input').at(0).prop('value')).toEqual("correo@correo.com");
expect(wrapper.find('input').at(1).prop('value')).toEqual("digitalcar123");
wrapper.find('input').at(0).simulate('change', { target: { name: 'email', value: "digitalcar@correo.com" } });
wrapper.find('input').at(1).simulate('change', { target: { name: 'password', value: "digitalcar4321" } });
expect(testState.email).toEqual("digitalcar@correo.com");
expect(testState.password).toEqual("digitalcar4321");*/

    const componenteInput = [{
        id: "testemail",
        name :"testemail",
        placeholder: "correo@correo.com",
        type : "email",
        value : "digitalcar@correo.com"
      },
     {
        id: "testpasword",
        name :"testpasword",
        placeholder: "digitalcar123",
        type : "password",
        value : "digitalcar4321"
      }]
    test("render add features ", () => {
        const wrapper = shallow(<Login/>)
        expect(wrapper).toMatchSnapshot();
    });
    
    test('email check',()=>{
        const onChangeSpy = jest.fn();
        const wrapper = shallow(<Login componente={componenteInput} handleChange ={onChangeSpy}/>);
        wrapper.find('.email').simulate('change', {target: 
            {name: 'email', value: "digitalcar@correo.com"}});
        expect(wrapper.state('email')).toEqual('digitalcar@correo.com');
    })

    test('password check',()=>{
        const wrapper = shallow(<Login componente={componenteInput}/>);
        wrapper.find('Inputs [type="password"]').simulate('change', {target: {name: 'password', value: "digitalcar123"}});
        expect(wrapper.state('password')).toEqual('digitalcar123');
    });

});