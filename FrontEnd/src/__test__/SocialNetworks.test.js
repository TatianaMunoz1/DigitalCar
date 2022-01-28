import "@testing-library/jest-dom/extend-expect";
import { render, screen } from "@testing-library/react";
import SocialNetworks from "../components/SocialNetworks/SocialNetworks";

describe("SocialNetworks", () => {
  test("must be exist", () => {
      const urls = ['https://www.facebook.com/',
      'https://www.linkedin.com/',
      'https://www.twitter.com/',
      'https://www.instagram.com/']
    render(<SocialNetworks />);
    const element = screen.getAllByRole("link");

    expect(element).toHaveLength(urls.length);

    element.forEach(e => {
        expect(urls.includes(e.href)).toBeTruthy();//todo mejorar?
    })
  });
});
