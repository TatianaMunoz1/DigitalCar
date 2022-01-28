import React from "react";
import { mount, shallow } from "enzyme";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import Administration from "../components/Administration/Administration.js";

describe("Administration", () => {
  const history = createMemoryHistory();
  const wrapper = mount(
    <Router history={history}>
      <Administration />
    </Router>
  );
  test("render administration ", () => {
    const wrapper = shallow(<Administration />);
    expect(wrapper).toMatchSnapshot();
  });

  test("must be exist", () => {
    expect(wrapper.text().includes("Agregar atributos")).toBeTruthy();
    expect(wrapper.text().includes("Cargar imágenes")).toBeTruthy();
    expect(wrapper.text().includes("Categoría")).toBeTruthy();
  });
});
