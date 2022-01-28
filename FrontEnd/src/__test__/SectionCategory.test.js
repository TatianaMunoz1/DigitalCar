import "@testing-library/jest-dom/extend-expect";
import { render, screen } from "@testing-library/react";
import CardCategory from "../components/CardCategory/CardCategory";
import SectionCategory from "../components/SectionCartegory/SectionCategory";

describe("SectionCategory", () => {
  const categories = [
    //para json
    {
      id: 1,
      title: "SUV",
      imgUrl: "google.com",
      description: "Mas de 20 modelos",
    },
    {
      id: 2,
      title: "Descapotable",
      imgUrl: "twitter.com",
      description: "Mas de 10 modelos",
    },
  ];
  test("must category exist", () => {
    render(<SectionCategory />);
    for (let i = 0; i < categories.length; i++) {
      const element = screen.queryByTitle(categories[i].title);
      expect(element).toBeInTheDocument();
    }
  });
});
