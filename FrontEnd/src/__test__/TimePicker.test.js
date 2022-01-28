import "@testing-library/jest-dom/extend-expect";
import { render, screen } from "@testing-library/react";
import TimePicker from "../components/TimePicker/TimePicker";

describe("TimePicker", () => {
  test("must be exist", () => {
    render(<TimePicker handleTimeReservation={() => {}} />);
    const element = screen.getByTestId("TimePickerTestId");

    expect(element).toBeInTheDocument();
    expect(element.hasChildNodes()).toBeTruthy();
  });
});
