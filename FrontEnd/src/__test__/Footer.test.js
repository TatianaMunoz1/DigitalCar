import React from "react";
import "@testing-library/jest-dom/extend-expect";
import { render, screen } from "@testing-library/react";
import { mount } from "enzyme";
import Footer from "../components/Footer/Footer";

describe("Footer", () => {
  test("render titulo del footer", () => {
    const footer = mount(<Footer />);
    expect(footer.find(".line").text()).toEqual("©2021 Digital Booking");
  });
});
