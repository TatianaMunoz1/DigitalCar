import React, { useEffect } from "react";
import { shallow, mount, render, mock } from "enzyme";
import { Router } from "react-router-dom";
import CategoryPicker from "../components/CategoryPicker/CategoryPicker.js"

describe("category picker", () => {
    test("render category picker ", () => {
        const wrapper = shallow(<CategoryPicker/>)
        expect(wrapper).toMatchSnapshot();
    });
});