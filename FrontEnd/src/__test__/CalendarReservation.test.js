import React from "react";
import { render, screen } from "@testing-library/react";
import CalendarReservation from "../components/CalendarReservation/CalendarResevation";

describe("Calendar Reservation", () => {
  test("must be exist", () => {
    render(<CalendarReservation />);
    setTimeout(() => {
      const element = screen.getByTestId("calendarReservationTestId");

      expect(element).toBeInTheDocument();
      expect(element.hasChildNodes()).toBeTruthy();
    }, 10000);
  });
});
