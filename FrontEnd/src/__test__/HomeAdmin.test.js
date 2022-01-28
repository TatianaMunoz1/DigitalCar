import React from "react";
import "@testing-library/jest-dom/extend-expect";
import { render, screen } from "@testing-library/react";
import { shallow, mount, mock } from "enzyme";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import HomeAdmin from '../pages/HomeAdmin/HomeAdmin'

describe("Button", () => {
    const data = {
        title:"Gestión de vehículos",
        link:"/administration",
        btnStyle:"btn--void",
        btnSize:"btn--medium"
    };
    const history = createMemoryHistory();
    

    test("render home admin ", () => {
        const wrapper = shallow(<HomeAdmin datos={data}/>)
        expect(wrapper).toMatchSnapshot();
    });

    test("must display button 1", () => {
      render(
        <Router history={history}>
          <HomeAdmin {...data} />
        </Router>
      );
      const element = screen.findByText("button")
      expect(element).toBeInTheDocument();
      expect(element).toHaveClass("btn");
      expect(element).toHaveClass(data.btnStyle);
      expect(element).toHaveClass(data.btnSize);
    });
});