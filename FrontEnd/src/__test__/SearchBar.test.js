import "@testing-library/jest-dom/extend-expect";
import { render, screen } from "@testing-library/react";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import SearchCityBar from "../components/SearchCityBar/SearchCityBar";
import Calendar from "../components/Calendar/Calendar";
import SearchBar from "../components/SearchBar/SearchBar";

describe("Search bar render elements", () => {
  const history = createMemoryHistory();
  test("must render title", () => {
    render(
      <Router history={history}>
        <SearchBar />
      </Router>
    );
    const element = screen.getByText(
      "Buscá las mejores ofertas en vehículos de alquiler"
    );
    expect(element).toBeInTheDocument();
  });
  test("must render calendar", () => {
    render(<Calendar />);
    const element = screen.getByTestId("calendarTestId");
    expect(element).toBeInTheDocument();
    expect(element.hasChildNodes()).toBeTruthy();
  });
  test("must render SearchCityBar", () => {
    render(<SearchCityBar />);
    const element = screen.getByTestId("searchCityBarTestId");
    expect(element).toBeInTheDocument();
    expect(element.hasChildNodes()).toBeTruthy();
  });
});
