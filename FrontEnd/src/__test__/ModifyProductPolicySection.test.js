import React from "react";
import { mount, shallow } from "enzyme";
import "@testing-library/jest-dom/extend-expect";
import { render, screen } from "@testing-library/react";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import ModifyProductPolicySection from "../components/ModifyProductPolicySection/ModifyProductPolicySection";


describe("Modify Product Policy", () => {

    test("render Modify Product Policy", () => {
        const wrapper = shallow(<ModifyProductPolicySection/>)
        expect(wrapper).toMatchSnapshot();
    });
});