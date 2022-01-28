import React from "react";
import { mount, shallow } from "enzyme";
import "@testing-library/jest-dom/extend-expect";
import { render, screen } from "@testing-library/react";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import App from '../App.js';

describe("App", () => {

    test("render  App ", () => {
        const wrapper = shallow(<App/>)
        expect(wrapper).toMatchSnapshot();
    });

    /*const history = createMemoryHistory();
    const wrapper = mount(
        <Router history={history}>
          <App />
        </Router>);

    test("must be exist", () => {
       expect(wrapper.text().includes("Busca las mejores ofertas en vehiculos de alquiler")).toBeTruthy();
        });*/
    });
