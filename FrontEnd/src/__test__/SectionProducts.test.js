import React from "react";
import { shallow, mount, render } from "enzyme";
import { createMemoryHistory } from "history";
import { Router } from "react-router-dom";
import SectionProduct from '../components/SectionProducts/SectionProducts.js';
import SectionProducts from "../components/SectionProducts/SectionProducts.js";
const cars =[ {
  id:3,
  title:"MINI One Cabrio",
  seats:4,
  doors:3,
  transmission: "Manual",
  baggage:1,
  description:"Esta oferta incluye 100km gratis! The fire truck braked the upfitted Express 1500. The mechanical Hino was developed by the Hino and once the tow truck totaled the seat belt.",
  category:"Descapotable",
  imgUrl: "https://www.sixt.com.ar/fileadmin/files/global/user_upload/fleet/png/350x200/mini-cooper-cabrio-2d-weiss-offen-2018.png"
},
{
  id:4,
  title:"VW T-Roc Convertible",
  seats:4,
  doors:2,
  transmission: "Automatica",
  baggage:1,
  description:"Esta oferta incluye 300km gratis! The fire truck braked the upfitted Express 1500. The mechanical Hino was developed by the Hino and once the tow truck totaled the seat belt.",
  category:"Descapotable",
  imgUrl: "https://www.sixt.com.ar/fileadmin/files/global/user_upload/fleet/png/350x200/vw-t-roc-convertible-2d-yellow-open-2020.png"
},
{
  id:5,
  title:"Mercedes Benz A-Class",
  seats:5,
  doors:5,
  transmission: "Automatica",
  baggage:1,
  description:"Esta oferta incluye 100km gratis! The fire truck braked the upfitted Express 1500. The mechanical Hino was developed by the Hino and once the tow truck totaled the seat belt.",
  category:"Luxury",
  imgUrl: "https://www.sixt.com.ar/fileadmin/files/global/user_upload/fleet/png/350x200/mb-a-klasse-5d-weiss-2018.png"
}];


describe("Section Product", () => {
  const history = createMemoryHistory();

  test("render section fav ", () => {
       const component = render(<SectionProducts {...cars} />);
     expect(component).toMatchSnapshot();
  });
     test("must display button 1", () => {
      render(
        <Router history={history}>
          <SectionProducts />
        </Router>
      );
      const element = screen.getByText(title);
      expect(element).toBeInTheDocument();
      expect(element).toHaveAttribute("type");
      expect(element).toHaveClass('btn--full');
      expect(element).toHaveClass("btn--responsive");
    });
   });
  
   
  
