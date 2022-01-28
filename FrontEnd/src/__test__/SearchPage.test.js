import React from "react";
import { mount, shallow } from "enzyme";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import SearchPage from '../pages/SearchPage/SearchPage.js';

describe("SearchPage", () => {
  const history = createMemoryHistory();
  test("render category picker ", () => {
    const wrapper = shallow(<SearchPage/>)
    expect(wrapper).toMatchSnapshot();

  /*test("must be exist", () => {
    const wrapper = shallow(<SearchPage/>);
    expect(wrapper.find("title"))*/
    //expect(wrapper.text().includes("Cargar nuevo vehículo")).toBeTruthy();
    //expect(wrapper.text().includes("Agregar atributos")).toBeTruthy();
    //expect(wrapper.text().includes("Cargar imágenes")).toBeTruthy();
  });

    
});