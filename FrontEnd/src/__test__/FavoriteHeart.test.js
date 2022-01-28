import React from "react";
import { mount } from "enzyme";
import FavoriteHeart from "../components/FavoriteHeart/FavoriteHeart";

describe("FavoriteHeart", () => {
  const wrapper = mount(<FavoriteHeart />);

  test("must render icon", () => {
    expect(wrapper.find("FontAwesomeIcon")).toHaveLength(1);
  });
  test("icon button click", () => {
    wrapper.find("FontAwesomeIcon").simulate("click");
  });
});