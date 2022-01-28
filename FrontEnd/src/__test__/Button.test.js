import React from "react";
import "@testing-library/jest-dom/extend-expect";
import { render, screen } from "@testing-library/react";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import Button from "../components/Button/Button";

describe("Button", () => {
  const data = {
    link: "/",
    title: "tituloTest",
    type: "text",
    btnStyle: "btn--full",
    btnSize: "btn--large",
  };
  const history = createMemoryHistory();

  test("must display button 1", () => {
    render(
      <Router history={history}>
        <Button {...data} />
      </Router>
    );
    const element = screen.getByText(data.title);
    expect(element).toBeInTheDocument();
    expect(element).toHaveAttribute("type", data.type);
    expect(element).toHaveClass("btn");
    expect(element).toHaveClass(data.btnStyle);
    expect(element).toHaveClass(data.btnSize);
  });

  test("must display button 2", () => {
    render(
      <Router history={history}>
        <Button {...data} btnStyle="otherStyle" btnSize="otherSize" />
      </Router>
    );
    const element = screen.getByText(data.title);
    expect(element).toBeInTheDocument();
    expect(element).toHaveClass("btn");
    
  });
});
