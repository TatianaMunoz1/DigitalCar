import "@testing-library/jest-dom/extend-expect";
import { render, screen } from "@testing-library/react";
import Calendar from "../components/Calendar/Calendar";

describe("Calendar", () => {
  test("must be exist", () => {
    render(<Calendar />);
    setTimeout(() => {
      
    const element = screen.getByTestId("calendarTestId");

    expect(element).toBeInTheDocument();
    expect(element.hasChildNodes()).toBeTruthy();
  }, 10000);
});
});
