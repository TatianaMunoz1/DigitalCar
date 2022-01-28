import React from "react";
import { mount, shallow } from "enzyme";
import "@testing-library/jest-dom/extend-expect";
import { render, screen } from "@testing-library/react";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import ProductDetails from "../pages/ProductDetails/ProductDetails";


describe(" Product Details", () => {

    test("render  Product Details", () => {
        const wrapper = shallow(<ProductDetails/>)
        expect(wrapper).toMatchSnapshot();
    });
});