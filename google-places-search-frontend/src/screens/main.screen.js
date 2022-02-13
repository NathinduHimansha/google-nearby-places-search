import React, { Component } from "react";
import "../assets/css/main.css";
import logo from "../assets/images/logo.png";
import Card from "../components/common/card.component";
import axios from "axios";
import * as Constant from "../constants";
import { Container, Row, Col } from "reactstrap";

class MainScreen extends Component {
  constructor(props) {
    super(props);

    this.state = {
      apiData: [],
      type: "",
      longitude: "",
      latitude: "",
      radius: "",
    };
  }

  requestHandler(type, longitude, latitude, radius) {
    console.log("called1");
    axios
      .get(
        `${Constant.SERVICE_URL}/api/v1/data/places/nearby?type=${type}&longitude=${longitude}&latitude=${latitude}&radius=${radius}`
      )
      .then((response) => {
        this.setState({ apiData: response.data.data });
        console.log("baw", response.data);
      })
      .catch((error) => {
        console.log("ERROR", error);
      });
  }

  filterHandler = (e) => {
    if (e !== null || e !== "") {
      if (e.target.name === "type") {
        this.setState({ type: e.target.value });
      } else if (e.target.name === "longitude") {
        this.setState({ longitude: e.target.value });
      } else if (e.target.name === "latitude") {
        this.setState({ latitude: e.target.value });
      } else if (e.target.name === "radius") {
        this.setState({ radius: e.target.value });
      }
    }
  };

  resetValues = () => {
    this.setState({
      type: "",
      longitude: "",
      latitude: "",
      radius: "",
      apiData: [],
    });
  };

  render() {
    return (
      <div>
        <div class="row">
          <div class="col-md-4">
            <nav class="navbar navbar-default">
              <div class="container-fluid">
                <div class="navbar-header">
                  <a class="logo pull-left" href="#">
                    <img src={logo} />
                  </a>

                  <span class="name left mx-1 large">
                    <b>Nearby&reg;</b>
                  </span>
                </div>
              </div>
            </nav>
          </div>
        </div>

        <div class="row mt-3">
          <div class="col-md-2">
            <input
              type="text"
              id="InputField"
              name="type"
              placeholder="Enter Place Type"
              value={this.state.type}
              onChange={this.filterHandler}
            />
          </div>
          <div class="col-md-2">
            <input
              type="text"
              id="InputField"
              name="longitude"
              placeholder="Enter Place Longitude"
              value={this.state.longitude}
              onChange={this.filterHandler}
            />
          </div>
          <div class="col-md-2">
            <input
              type="text"
              id="InputField"
              name="latitude"
              placeholder="Enter Place Latitude"
              value={this.state.latitude}
              onChange={this.filterHandler}
            />
          </div>
          <div class="col-md-2">
            <input
              type="text"
              id="InputField"
              name="radius"
              placeholder="Enter Place Radius"
              value={this.state.radius}
              onChange={this.filterHandler}
            />
          </div>
          <div class="col-md-2">
            <button
              type="button"
              className="rounded-pill btn btn-danger mr-2"
              disabled={
                this.state.type === "" ||
                this.state.latitude === "" ||
                this.state.longitude === "" ||
                this.state.radius === ""
                  ? "true"
                  : ""
              }
              onClick={() =>
                this.requestHandler(
                  this.state.type,
                  this.state.longitude,
                  this.state.latitude,
                  this.state.radius
                )
              }
            >
              <i className="bi bi-search mx-1" />
              Find
            </button>

            <button
              type="button"
              className="rounded-pill btn btn-danger ml-2"
              onClick={() => this.resetValues()}
            >
              <i className="bi bi-x-circle mx-1" />
              Reset
            </button>
          </div>
        </div>
        <div class="row">
          <hr class="mt-3" />
        </div>

        <Container>
          <Row>
            {this.state.apiData
              ? this.state.apiData.length != 0
                ? this.state.apiData.map((data) => (
                    <Col xs="3">
                      <div>
                        <Card
                          logo={data.photoUrl}
                          name={data.name}
                          address={data.address}
                        />
                      </div>
                    </Col>
                  ))
                : ""
              : ""}
          </Row>
        </Container>
      </div>
    );
  }
}

export default MainScreen;
