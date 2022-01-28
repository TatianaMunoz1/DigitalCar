import React, { useState, useEffect } from "react";
import styles from "./Rating.module.css";

function Rating(props) {
  const [arrayRatings, setArrayRatings] = useState([5, 10, 8, 8, 5]);
  const [ratings, setRatings] = useState(0);
  const [ratingLabel, setRatingLabel] = useState("Sin calificar");

  useEffect(() => {
    if (arrayRatings.length >= 1) {
      let sum = 0;
      arrayRatings.forEach((rating) => {
        sum += rating;
      });
      let prom = Math.round((sum / arrayRatings.length) * 2) / 2;
      setRatings(prom);

      document.documentElement.style.setProperty("--rating", `${ratings}`);

      if (ratings < 3) {
        setRatingLabel("Muy malo");
      } else if (ratings >= 3 && ratings < 5) {
        setRatingLabel("Malo");
      } else if (ratings >= 5 && ratings < 7) {
        setRatingLabel("Bueno");
      } else if (ratings >= 7 && ratings < 9) {
        setRatingLabel("Muy bueno");
      } else if (ratings >= 9) {
        setRatingLabel("Excelente");
      }
    }
  }, [ratings]);

  return (
    <div className={styles.puntuation_box} data-testid="ratingTestId">
      <div className={styles.puntuation}>
        <p style={{ display: props.dontShowLabel ? "none" : "contents" }}>
          {ratingLabel}
        </p>
        <div
          className={styles.puntuation_stars}
          style={{ display: props.dontShowStars ? "none" : "contents" }}
        >
          <div className={styles.stars_rating}></div>
        </div>
      </div>
      <div
        className={styles.puntuation_number}
        style={{ display: props.dontShowRating ? "none" : "block" }}
      >
        {ratings}
      </div>
    </div>
  );
}

export default Rating;
