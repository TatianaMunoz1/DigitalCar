import React, { useState, useEffect } from "react";
import styles from "./AddFeatures.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus, faTimes } from "@fortawesome/free-solid-svg-icons";
import Swal from "sweetalert2";
import FeaturePicker from "../FeaturePicker/FeaturePicker";

function AddFeatures(props) {
  const [listFeatures, setListFeatues] = useState(null);
  const [validation, setValidation] = useState(false);
  const [newCarFeatures, setNewCarFeatures] = useState([]);
  const [newCarFeature, setNewCarFeature] = useState({
    name: "",
    icon: "",
  });
  const [featureValidation, setFeatureValidation] = useState({
    name: null,
    icon: null,
  });

  useEffect(() => {
    props.handleAddNewFeatures([...newCarFeatures]);
  }, [newCarFeatures]);

  const handleAddFeature = () => {
    if (featureValidation.name === true && featureValidation.icon === true) {
      setValidation(true);
      setNewCarFeatures([...newCarFeatures, newCarFeature]);
      setNewCarFeature({
        name: "",
        icon: "",
      });
    } else {
      setValidation(false);
    }
  };

  const handleFeaturePicker = (feature) => {
    if (feature !== null && feature !== undefined) {
      setNewCarFeature({
        name: feature.name,
        icon: feature.icon,
      });
      setNewCarFeatures([
        ...newCarFeatures,
        {
          name: feature.name,
          icon: feature.icon,
        },
      ]);
      setValidation(true);
    }
  };

  const handleDeleteFeature = (e) => {
    Swal.fire({
      title: "¿Desea borrar el atributo?",
      icon: "warning",
      showCancelButton: true,
      background: "#f3f3f3",
      confirmButtonColor: "#b40505",
      cancelButtonColor: "#000000",
      confirmButtonText: "Borrar",
      cancelButtonText: "No borrar",
    }).then((result) => {
      if (result.isConfirmed) {
        const idImage = e.target.id;
        const array = [...newCarFeatures];
        array.splice(idImage, 1);
        setNewCarFeatures([...array]);
      }
    });
  };

  useEffect(() => {
    const listFeature = () =>
      newCarFeatures.map((carFeature, index) => (
        <div
          key={`${carFeature.name}_${index}`}
          className={styles.new_feature_box}
        >
          <div className={styles.new_feature}>
            <input
              className={styles.input}
              type="text"
              value={carFeature.name}
              disabled
            />
          </div>
          <div className={styles.btn_new_feature_container}>
            <button
              className={styles.btn_delete_feature}
              type="button"
              id={index}
              onClick={(e) => handleDeleteFeature(e)}
            >
              <FontAwesomeIcon icon={faTimes} />
            </button>
          </div>
        </div>
      ));
    setListFeatues(listFeature);
  }, [newCarFeatures]);

  return (
    <div className={styles.features}>
      <div className={styles.new_features}>
        {newCarFeatures.length > 0 && <div>{listFeatures}</div>}
      </div>

      <div className={styles.add_features_section}>
        <div className={styles.add_features}>
          <FeaturePicker handleFeaturePicker={handleFeaturePicker} />
        </div>
        <div className={styles.btn_container}>
          <button
            className={styles.btn_add_feature}
            type="button"
            onClick={handleAddFeature}
          >
            <FontAwesomeIcon icon={faPlus} />
          </button>
        </div>
      </div>
    </div>
  );
}

export default AddFeatures;
