import React from "react";
import { Router } from "react-router-dom";
import { shallow, mount } from "enzyme";
import SearchCityBar from "../components/SearchCityBar/SearchCityBar";
import { act, fireEvent, render, screen } from "@testing-library/react";

const cities = [
  {
    id: 1,
    type: "aeropuerto",
    name: "Bariloche",
    country: "Argentina",
  },
  {
    id: 2,
    type: "ciudad",
    name: "Bariloche Centro",
    country: "Argentina",
  },
];

describe("Search city bar", () => {
  test("renders no cities when data is empty", () => {
    const wrapper = shallow(<SearchCityBar data={[]} />);
    expect(wrapper.find(".options").length).toBe(0);
  });

  test("renders input", () => {
    const wrapper = shallow(<SearchCityBar />);
    expect(wrapper.find(".input").at(0).props().placeholder).toEqual(
      "Ingrese ciudad o aeropuerto"
    );
  });

  test("click on one city of SearchCityBar", () => {
    const wrapper = mount(<SearchCityBar {...cities} />);
    wrapper.find(`#${cities.id}`).map((e) => e.simulate("click"));
  });
});
