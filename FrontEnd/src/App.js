import React, { useState, useEffect } from "react";
import { BrowserRouter, Switch, Route, Redirect } from "react-router-dom";
import Header from "./components/Header/Header";
import Footer from "./components/Footer/Footer";
import Home from "./pages/Home/Home";
import Login from "./pages/Login/Login";
import Register from "./pages/Register/Register";
import ProductDetails from "./pages/ProductDetails/ProductDetails";
import Reservation from "./pages/Reservation/Reservation";
import SuccesReservation from "./pages/SuccesReservation/SuccesReservation";
import SuccesAddNewProduct from "./pages/SuccesAddNewProduct/SuccesAddNewProduct";
import SearchPage from "./pages/SearchPage/SearchPage";
import ReservationByUser from "./pages/ReservationByUser/ReservationByUser";
import HomeAdmin from "./pages/HomeAdmin/HomeAdmin";
import PrivateRoute from "./components/PrivateRoute/PrivateRoute";

function App() {
  const [user, setUser] = useState({
    id: 0,
    name: "",
    lastName: "",
    email: "",
    token: "",
    role: "",
  });
  const [renderHeader, setRenderHeader] = useState(1);
  const [isUser, setIsUser] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);

  useEffect(() => {
    let userLocalStorage = localStorage.getItem("user");

    if (userLocalStorage) changeUser(JSON.parse(userLocalStorage));
  }, []);

  const changeUser = (newUser) => {
    setUser(newUser);
    localStorage.setItem("user", JSON.stringify(newUser));
    if (newUser.role === "user") {
      setIsUser(true);
      setIsAdmin(false);
    } else if (newUser.role === "admin") {
      setIsUser(false);
      setIsAdmin(true);
    }
  };

  const renderHeaderToggle = () => {
    setRenderHeader(renderHeader + 1);
  };

  return (
    <div className="App">
      <BrowserRouter>
        <Route path="/">
          <Header
            user={user}
            changeUser={changeUser}
            renderHeader={renderHeader}
          />
        </Route>
        <Switch>
          <Route exact path="/">
            <Home renderHeader={renderHeaderToggle} />
          </Route>
          <Route path="/login">
            {user.name === "" ? (
              <Login
                changeUser={changeUser}
                renderHeader={renderHeaderToggle}
              />
            ) : (
              <Redirect to="/" />
            )}
          </Route>
          <Route path="/register">
            {user.name === "" ? (
              <Register
                changeUser={changeUser}
                renderHeader={renderHeaderToggle}
              />
            ) : (
              <Redirect to="/" />
            )}
          </Route>
          <Route path="/product-detail/:carId" children={<ProductDetails />} />
          <Route path="/:carId/reservation">
            {user.name !== "" ? (
              <Reservation user={user} />
            ) : (
              <Redirect to="/login" />
            )}
          </Route>
          <Route path="/search-page" children={<SearchPage />} />
          <Route
            path="/reservation-by-user"
            children={<ReservationByUser userId={user.id} />}
          />
          <Route path="/succes-reservation" children={<SuccesReservation />} />

          <PrivateRoute
            path="/home-admin"
            auth={isAdmin}
            children={<HomeAdmin user={user}/>}
          />
          <PrivateRoute
            path="/succes-add-new-product"
            auth={isAdmin}
            children={<SuccesAddNewProduct />}
          />
        </Switch>
        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
