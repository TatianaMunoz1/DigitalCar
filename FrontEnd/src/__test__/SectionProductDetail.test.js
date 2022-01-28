import React, { useEffect } from "react";
import { shallow, mount, render, mock } from "enzyme";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";
import SectionProductDetail from "../components/SectionProductDetail/SectionProductDetail.js";

const car = {
  id: 3,
  title: "MINI One Cabrio",
  seats: 4,
  doors: 3,
  transmission: "Manual",
  baggage: 1,
  description:
    "Esta oferta incluye 100km gratis! The fire truck braked the upfitted Express 1500. The mechanical Hino was developed by the Hino and once the tow truck totaled the seat belt.",
  category: "Descapotable",
  imgUrl:
    "https://www.sixt.com.ar/fileadmin/files/global/user_upload/fleet/png/350x200/mini-cooper-cabrio-2d-weiss-offen-2018.png",
};

describe("Section Product Detail", () => {
  const history = createMemoryHistory();
  const wrapper = mount(
    <Router history={history}>
      <SectionProductDetail />
    </Router>
  );
  test("must be exist", () => {
    expect(wrapper.text().includes("Características")).toBeTruthy();
    expect(wrapper.text().includes("Esta oferta incluye")).toBeTruthy();
    expect(wrapper.text().includes("Fechas disponibles")).toBeTruthy();
    expect(
      wrapper
        .text()
        .includes("Elegí tus fechas de viaje para obtener los mejores precios.")
    ).toBeTruthy();
    expect(wrapper.text().includes("¿Dónde vas a estar?")).toBeTruthy();
  });

  test("render section Detail", () => {
    const component = render(<SectionProductDetail {...car} />);
    expect(component).toMatchSnapshot();
    expect(component.text().includes("Esta oferta incluye")).toBeTruthy();
  });

  test("render section Detail test", () => {
    const component = shallow(<SectionProductDetail />);
    expect(component).toMatchSnapshot();
    //expect(component.text().includes(car.description)).toBeTruthy();
  });

  /*jest.mock('react-router-dom', () => 
({ ...jest.requireActual('react-router-dom'),
  useParams: () => ({ 
      carId: 3, 
     }), 
  useRouteMatch: () => 
  ({ url: 'http://localhost:8080/cars/3' }), }));*/
});
