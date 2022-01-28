import React from "react";
import { mount } from "enzyme";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import Subheader from "../components/Subheader/Subheader";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

describe("Subheader", () => {
  const history = createMemoryHistory();

  const wrapper = mount(
    <Router history={history}>
      <Subheader
        Subheader
        title={"Testeo de Subheader"}
        subtitle={"Test"}
        link={"/"}
      />
    </Router>
  );

  test("must render text", () => {
    expect(wrapper.text().includes("Testeo de Subheader")).toBeTruthy();
    expect(wrapper.text().includes("Test")).toBeTruthy();
  });
  test("must render icon", () => {
    expect(wrapper.find("FontAwesomeIcon")).toHaveLength(1);
  });
  test("icon button click", () => {
    wrapper.find("FontAwesomeIcon").simulate("click");
  });
});
