import React from 'react';
import { shallow, mount } from 'enzyme';
import { useHistory, useLocation } from "react-router-dom";
import { render, screen } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import Header from '../components/Header/Header.js';
import Button from "../Button/Button";
import SocialNetworks from "../SocialNetworks/SocialNetworks";

jest.mock(useHistory);
jest.mock(useLocation);

 describe("<Header/>", () => {

  const location= {
      title:"Crear cuenta",
      link:"/register",
      btnStyle:"btn--void",
      btnSize:"btn--medium"
    }
  const history ={
    link:"/"
  }
useLocation.mockImplementation(() => location)

it('has same amount of cards as participants are provided', () => {
  render(<Header />)
  expect(screen.getAllByTestId('location-register'));
  expect(element).toBeInTheDocument();
});

    test('should test Header component', () => {
      const wrapper = shallow(<Header {...usersTest}/>);
      expect(wrapper).toMatchSnapshot();
  });

  test('must render icon ', () => {
    const wrapper = mount(<Header/>);
    expect(wrapper.find("FontAwesomeIcon")).toHaveLength(1);
});
  




});

