import React from "react";
import { mount } from "enzyme";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import Reservation from "../pages/Reservation/Reservation";

describe("Reservation", () => {
  const history = createMemoryHistory();
  const user = [
    {
      id: 0,
      name: "Juan",
      lastName: "Perez",
      email: "juanperez@gmail.com",
      token: "",
    },
  ];

  const wrapper = mount(
    <Router history={history}>
      <Reservation user={user} />
    </Router>
  );
  test("must be exist", () => {
    expect(wrapper.text().includes("Completá tus datos")).toBeTruthy();
    expect(
      wrapper.text().includes("Seleccioná tu fecha de reserva")
    ).toBeTruthy();
    expect(wrapper.text().includes("Tu horario de reserva")).toBeTruthy();
  });
});