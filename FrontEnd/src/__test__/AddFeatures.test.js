import React, { useEffect } from "react";
import { shallow, mount, render, mock } from "enzyme";
import { Router } from "react-router-dom";
import AddFeatures from "../components/AddFeatures/AddFeatures";

describe("add features", () => {
    test("render add features ", () => {
        const wrapper = shallow(<AddFeatures/>)
        expect(wrapper).toMatchSnapshot();
    });

    test('should call handleInputFeatureChange method', () => {
        const onClickSpy = jest.fn();
        const wrapper = shallow(<AddFeatures handleInputFeatureChange={onClickSpy} />);
        expect(wrapper).toMatchSnapshot();
        wrapper.find('button').simulate('click');
        expect(onClickSpy).toHaveBeenCalled();
});

});