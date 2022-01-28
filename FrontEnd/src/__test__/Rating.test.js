import React from "react";
import { render, screen } from "@testing-library/react";
import Rating from "../components/Rating/Rating";

describe("Ratings", () => {
  const ratings = [5, 10, 8, 8, 5];
  render(<Rating {...ratings} />);
  test("must render", () => {
    setTimeout(() => {
      const element = screen.getByTestId("ratingTestId");
      expect(element).toBeInTheDocument();
    }, 10000);
  });
  test("must render stars", () => {
    setTimeout(() => {
      const element = screen.getByTestId("ratingTestId");
      expect(element).toHaveClass("stars_rating");
    }, 10000);
  });
});
