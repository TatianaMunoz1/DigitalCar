import React, { useState, useEffect } from "react";
import styles from "./FeaturePicker.module.css";
import axios from "axios";
import Url from "../../Url"

function FeaturePicker(props) {
  const [features, setFeatures] = useState([]);

  useEffect(() => {
    axios
      .get(Url+"/features")
      .then((response) => {
        setFeatures(response.data);
      })
      .catch((error) => {});
  }, []);

  // useEffect(() => {
  //   if (featureId !== null && featureId !== "")
  //     // props.handleFeaturePicker(features[featureId]);
  //     props.handleFeaturePicker(featureId);
  // }, [featureId]);

  // useEffect(() => {
  //   if (featureId !== null && featureId !== "")
  //     props.handleFeaturePicker(features[featureId]);
  // }, [featureId, features]);

  // const featureOptions = features.map((feature) => (
  //   <option
  //     key={`${feature.id}_${feature.name}`}
  //     id={feature.id.parseInt}
  //     value={feature.id}
  //   >
  //     {/* <i className={`${feature.icon}`}></i> */}
  //     {feature.name}
  //   </option>
  // ));

  return (
    <div className={styles.feature}>
      {/* <select
        name="features"
        onChange={(e) => props.handleFeaturePicker(features[e.target.value])}
      >
        <option
          selected
          value=""
          disabled
          hidden
          className={styles.placeholder}
        >
          Seleccione una característica
        </option>
        {featureOptions}
      </select> */}
      <select
        name="features"
        onChange={(e) =>
          // setFeatureId(e.target.value)
          props.handleFeaturePicker(features[e.target.value])
        }
        defaultValue={"Seleccione una característica"}
      >
        <option selected disabled hidden className={styles.placeholder}>
          Seleccione una característica
        </option>
        {features.length > 0 &&
          features.map((feature, index) => (
            <option value={index}>{feature.name}</option>
          ))}
      </select>
    </div>
  );
}

export default FeaturePicker;
