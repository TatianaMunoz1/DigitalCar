import React from "react";
import { mount, shallow } from "enzyme";
import "@testing-library/jest-dom/extend-expect";
import { render, screen } from "@testing-library/react";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import PrivateRoute from "../components/PrivateRoute/PrivateRoute";



describe("Private Route", () => {

    test("render  Private Route ", () => {
        const wrapper = shallow(<PrivateRoute/>)
        expect(wrapper).toMatchSnapshot();
    });
});