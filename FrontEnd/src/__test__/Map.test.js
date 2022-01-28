import React from "react";
import "@testing-library/jest-dom/extend-expect";
import { render, screen } from "@testing-library/react";
import Map from "../components/MapComponent/Map";

describe("Map", () => {
  test("Must exist", () => {
    render(<Map />);
    setTimeout(() => {
      const element = screen.getByTestId("testIdMap");
      expect(element).toBeInTheDocument();
    }, 10000);
  });
});
