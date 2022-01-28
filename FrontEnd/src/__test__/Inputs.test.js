import React from "react";
import '@testing-library/jest-dom'
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import Inputs from '../components/Inputs/Inputs.js';


const componenteInput = {
    class: "input",
    id: "testemail",
    name :"testemail",
    placeholder: "correo@correo.com",
    type : "email",
  }

describe ('<Inputs/>',() => {
 
    test('render email input', () => {
      render(<Inputs {...componenteInput} />);
      const element = screen.getByTestId("testemail");
      expect(element).toBeInTheDocument();
    });
   
   test('pass valid email to test email input field', () => {
      render(<Inputs {...componenteInput}/>);
   
      const element  = screen.getByTestId(componenteInput.id);
      userEvent.type(element, "correo@correo.com")

      expect(screen.getByTestId("testemail")).toHaveValue("correo@correo.com");
    });
   
    {/* test('pass invalid email to test input value', () => {
      render(<Inputs {...componenteInput} />);
   
      const inputEl = screen.getByText(componenteInput.name);
      userEvent.type(inputEl, "test");
   
      expect(screen.getByText(componenteInput.name)).toHaveValue('testemail');
    });*/}
   
  });
/*import React from "react";
import { Router } from "react-router-dom";
import { shallow, mount } from "enzyme";
import { render, screen } from "@testing-library/react";
import Inputs from "../../components/Inputs/Inputs";

describe("Input", () => {
  test("should change when input data into field", () => {
    const onChange = jest.fn();
    const e = {
      preventDefault() {},
      target: { value: "the-value" },
    };
    const valido = "true";

    const component = shallow(<Inputs onChange={onChange} valido={valido} />);
    component.find("input").simulate("change", e);
    expect(onChange).toBeCalledWith("the-value");
  });
});*/

/* no anda DX */
