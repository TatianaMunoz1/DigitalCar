import { Route, Redirect } from "react-router-dom";

export default function PrivateRoute(props) {
    return props.auth ? <Route {...props} /> : <Redirect to="/" />
}