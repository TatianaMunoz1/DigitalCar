import React from "react";
import "@testing-library/jest-dom/extend-expect";
import { mount } from "enzyme";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import ShareButton from "../components/ShareButton/ShareButton.js";

describe("Share Button", () => {
  const data = {
    title: "Digital Car",
  };

  const history = createMemoryHistory();
  const wrapper = mount(
    <Router history={history}>
      <ShareButton
        Subheader
        title={"Testeo de Subheader"}
        subtitle={"Test"}
        link={"/"}
      />
    </Router>
  );

  test("must render icon", () => {
    expect(wrapper.find("FontAwesomeIcon")).toHaveLength(1);
  });
  test("icon button click", () => {
    wrapper.find("FontAwesomeIcon").simulate("click");
  });
});
