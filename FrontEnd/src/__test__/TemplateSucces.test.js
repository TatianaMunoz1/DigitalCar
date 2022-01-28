import React from "react";
import { mount } from "enzyme";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import TemplateSucces from "../components/TemplateSucces/TemplateSucces.js";

describe("TemplateSucces", () => {
  const history = createMemoryHistory();

  const wrapper = mount(
    <Router history={history}>
      <TemplateSucces title={"Testeo de TemplateSucces"} subtitle={"Test"} />
    </Router>
  );

  test("must img exist", () => {
    expect(wrapper.find("img").prop("src")).toEqual("SuccesIcon.png");
    expect(wrapper.find("img").prop("alt")).toEqual("SuccesIcon");
  });
  test("must render text", () => {
    expect(wrapper.text().includes("Testeo de TemplateSucces")).toBeTruthy();
    expect(wrapper.text().includes("Test")).toBeTruthy();
  });
  test("button click", () => {
    wrapper.find(`button`).simulate("click");
  });
});
