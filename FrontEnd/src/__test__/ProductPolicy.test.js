import React from "react";
import "@testing-library/jest-dom/extend-expect";
import { render } from "@testing-library/react";
import ProductPolicy from "../components/ProductPolicy/ProductPolicy";


describe ("ProductPolicy",()=>{

    test ('must exist p', ()=>{
        render(<ProductPolicy  />);
        const element = document.querySelector('p');
        expect(element).toBeInTheDocument();

    });

    test ('must exist li',()=>{
        render (<ProductPolicy />);
        const element = document.querySelector('li');
        expect(element).toBeInTheDocument();

    });

    test ('must exist ul',() =>{
        render(<ProductPolicy />);
        const element = document.querySelector('ul');
        expect(element).toBeInTheDocument();
    })






})