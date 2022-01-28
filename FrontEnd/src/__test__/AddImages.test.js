import React, { useEffect } from "react";
import { shallow, mount, render, mock } from "enzyme";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import AddImages from "../components/AddImages/AddImages.js";

describe("add images", () => {
  const history = createMemoryHistory();
  const wrapper = mount(
    <Router history={history}>
      <AddImages />
    </Router>
  );
  test("must be exist", () => {
    expect(wrapper.text().includes("*Faltan completar campos")).toBeTruthy();
  });
  test("render add images ", () => {
    const wrapper = shallow(<AddImages />);
    expect(wrapper).toMatchSnapshot();
  });

  test("should call handleInputFeatureChange method", () => {
    const onClickSpy = jest.fn();
    const wrapper = shallow(<AddImages handleInputImageChange={onClickSpy} />);
    expect(wrapper).toMatchSnapshot();
    wrapper.find("button").simulate("click");
    expect(onClickSpy).toHaveBeenCalled();
  });
  test("must render icon", () => {
    expect(wrapper.find("FontAwesomeIcon")).toHaveLength(1);
  });
  test("icon button click", () => {
    wrapper.find("FontAwesomeIcon").simulate("click");
  });
});
