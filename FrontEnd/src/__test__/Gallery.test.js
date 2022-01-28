import React from "react";
import "@testing-library/jest-dom/extend-expect";
import { render, screen } from "@testing-library/react";
import { shallow, mount,  } from "enzyme";
import { MemoryRouter } from "react-router"
import FsLightbox from "fslightbox-react";
import Gallery from "../components/Gallery/Gallery";

let findByTestAttr = (wrapper, val) => wrapper.find(`[data-test='${val}']`);

describe("<Gallery/>", () => {
  test("Debe renderizar", () => {
    const wrapper = shallow(<Gallery imagenes={[]} />);
    expect(wrapper.find("div").first().hasClass("gallery-container")).toBe(
      true
    );
    expect(wrapper.find(FsLightbox)).toBeDefined();
  });

  test('Renderiza div con el texto "Ver más"', () => {
    const wrapper = shallow(<Gallery imagenes={[]} />);
    expect(wrapper.find("div").first().text()).toEqual("Ver más<l />");
  });

  test("Renderiza imagenes, comprueba su total, sus alt y src", () => {
    const wrapper = shallow(<Gallery imagenes={[
      {
        url: "https://i.pinimg.com/originals/a7/3d/b3/a73db38375a5a59fd2774f0f8ebda49f.jpg",
        titulo: "Sir Cat",
      },
      {
        url: "https://i.pinimg.com/564x/25/fd/60/25fd60383fd1c90601b8abb07aa93187.jpg",
        titulo: "Harry pawter",
      }
    ]}/>);

    const imagenes = wrapper.find('img');
    expect(imagenes.length).toBe(2);

    const altImagenes = imagenes.map( im => im.props().alt);
    expect(altImagenes).toEqual([
      'Sir Cat', 
      'Harry pawter'
    ]);

    const srcImagenes = imagenes.map( im => im.props().src);
    expect(srcImagenes).toEqual([
      "https://i.pinimg.com/originals/a7/3d/b3/a73db38375a5a59fd2774f0f8ebda49f.jpg",
      "https://i.pinimg.com/564x/25/fd/60/25fd60383fd1c90601b8abb07aa93187.jpg"
    ]);
  });
});



/*describe("Gallery", () => {
  test("render gallery ", () => {
      const wrapper = shallow(<Gallery/>)
      expect(wrapper).toMatchSnapshot();
  });
});
*/

/*describe("Button", () => {
  //     test("renders correctly", () => {
  //         const testProduct = {
  //            images: [
  //               {
  //                  url: ''
  //               }
  //            ]
  //         };
  //         shallow(<ImageGalleryStatic product={testProduct} />);
  //   });

  const testProduct = {
    images: [
      {
        id: 1,
        original:
          "https://www.chevrolet.com.ar/content/dam/chevrolet/mercosur/argentina/espanol/index/todos-los-vehiculos/02-images/cruze-sedan.png?imwidth=419",
        thumbnail:
          "https://www.chevrolet.com.ar/content/dam/chevrolet/mercosur/argentina/espanol/index/todos-los-vehiculos/02-images/cruze-sedan.png?imwidth=419",
      },
      {
        id: 2,
        original:
          "https://www.chevrolet.com.ar/content/dam/chevrolet/mercosur/argentina/espanol/index/todos-los-vehiculos/02-images/cruze-sedan.png?imwidth=419",
        thumbnail:
          "https://www.chevrolet.com.ar/content/dam/chevrolet/mercosur/argentina/espanol/index/todos-los-vehiculos/02-images/cruze-sedan.png?imwidth=419",
      },
    ],
  };
  const history = createMemoryHistory();

  //   test("must display button 1", () => {
  //     const wrapper = mount(
  //       //   <Router history={history}>
  //       <Gallery {...carImages} />
  //       //   </Router>
  //     );

  //     expect(wrapper.find("img").prop("src")).toEqual("");
  //     // const element = screen.getByText(data.title);
  //     expect(element).toBeInTheDocument();
  //     // expect(element).toHaveAttribute("type", data.type);
  //     // expect(element).toHaveClass("btn");
  //     // expect(element).toHaveClass(data.btnStyle);
  //     // expect(element).toHaveClass(data.btnSize);
  //   });

  describe("Gallery", () => {
    // test("must be exist", () => {
    const wrapper = mount(<Gallery carImages={testProduct} />);

    //     //   setTimeout(() => {
    //     //     const element = screen.getByTestId("galleryTestId");

    //     //     expect(element).toBeInTheDocument();
    //     //     expect(element.hasChildNodes()).toBeTruthy();
    //     //   });
    //     // }, 10000);
    //     //   });
    // })
    setTimeout(() => {
      test("button click", () => {
        wrapper.find(`button`).simulate("click");
      });

      // const element = screen.getByTestId("ratingTestId");
      // expect(element).toHaveClass("stars_rating");
    }, 10000);
  });
});*/
