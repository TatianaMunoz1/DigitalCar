import React from "react";
import "@testing-library/jest-dom/extend-expect";
import { render, screen } from "@testing-library/react";
import CardCategory from "../components/CardCategory/CardCategory";

describe("CardCategory", () => {
  const data = {
    id: 1,
    title: "testCard",
    description: "desc test card",
    img: "www.google.com",
  };

  test("must exist h3", () => {
    render(<CardCategory {...data} />);
    const element = screen.getByText(data.title);
    expect(element).toBeInTheDocument();
  });
  test("must exist p", () => {
    render(<CardCategory {...data} />);
    const element = screen.getByText(data.description);
    expect(element).toBeInTheDocument();
  });
  test("must exist img and should have src attribute", () => {
    render(<CardCategory {...data} />);
    const element = screen.getByAltText(data.title);
    expect(element).toBeInTheDocument();
    expect(element).toHaveAttribute("src", data.img);
  });
});
