import React from "react";
import { mount } from "enzyme";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import SuccesReservation from "../pages/SuccesReservation/SuccesReservation";

describe("SuccesReservation", () => {
  const history = createMemoryHistory();

  const wrapper = mount(
    <Router history={history}>
      <SuccesReservation />
    </Router>
  );

  test("must img exist", () => {
    expect(wrapper.find("img").prop("src")).toEqual("SuccesIcon.png");
    expect(wrapper.find("img").prop("alt")).toEqual("SuccesIcon");
  });
  test("must render text", () => {
    expect(wrapper.text().includes("¡Muchas gracias!")).toBeTruthy();
    expect(
      wrapper.text().includes("Su reserva se ha realizado con éxito.")
    ).toBeTruthy();
  });
  test("button click", () => {
    wrapper.find(`button`).simulate("click");
  });
});
