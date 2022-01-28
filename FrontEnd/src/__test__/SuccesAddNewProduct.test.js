import React from "react";
import { mount } from "enzyme";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import SuccesAddNewProduct from "../pages/SuccesAddNewProduct/SuccesAddNewProduct";

describe("SuccesAddNewProduct", () => {
  const history = createMemoryHistory();

  const wrapper = mount(
    <Router history={history}>
      <SuccesAddNewProduct />
    </Router>
  );

  test("must img exist", () => {
    expect(wrapper.find("img").prop("src")).toEqual("SuccesIcon.png");
    expect(wrapper.find("img").prop("alt")).toEqual("SuccesIcon");
  });
  test("must render text", () => {
    expect(
      wrapper.text().includes("Tu vehículo se ha creado con éxito.")
    ).toBeTruthy();
  });
  test("button click", () => {
    wrapper.find(`button`).simulate("click");
  });
});
