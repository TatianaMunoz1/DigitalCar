import React from "react";
import { Router } from "react-router-dom";
import { shallow, mount } from "enzyme";
import { render, screen } from "@testing-library/react";
import { createMemoryHistory } from "history";
import ReservationFormAlt from "../components/ReservationForm/ReservationFormAlt";

const usersTest = [
  (dataName = {
    type: "text",
    id: "name",
    value: "Juan",
  }),
  (dataLastName = {
    type: "text",
    id: "lastname",
    value: "Perez",
  }),
  (dataEmail = {
    type: "text",
    id: "email",
    value: "JuanPerez@correo.com",
  }),
];

const history = createMemoryHistory();

describe("ReservationForm", () => {
  it("should call onTest when mounting", () => {
    const spy = jest.fn();
    shallow(<ReservationFormAlt onTest={spy} />);

    expect(spy.called).to.equals(true);
  });

  test("should render value from render prop", () => {
    const wrapper = shallow(<ReservationFormAlt />);
    const childWrapper = wrapper
      .find(Content)

      .renderProp("user")({ name: "Juan" });

    expect(childWrapper.text()).to.equals("Juan");
  });

  test("render user data in forms", () => {
    const wrapper = mount(<ReservationFormAlt {...usersTest} />);
    setTimeout(() => {
      expect(wrapper.find(".name").text()).toEqual("Juan");
      expect(wrapper.find(".lastname").text()).toEqual("Perez");
      expect(wrapper.find(".mail").text()).toEqual("juanperez@gmail.com");
    }, 10000);
  });

  test("must be exist", () => {
    const wrapper = mount(
      <Router history={history}>
        <ReservationFormAlt />
      </Router>
    );

    expect(wrapper.text().includes("Nombre")).toBeTruthy();
    expect(wrapper.text().includes("Apellido")).toBeTruthy();
    expect(wrapper.text().includes("Correo electrónico")).toBeTruthy();
    expect(wrapper.text().includes("Vacunado contra el COVID-19")).toBeTruthy();
  });
});
