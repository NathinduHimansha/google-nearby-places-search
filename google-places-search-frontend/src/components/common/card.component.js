import React, { Component } from "react";

class Card extends Component {
  render() {
    return (
      <div class="card">
        <img
          class="card-img-top"
          width="250"
          height="300"
          src={this.props.logo}
        />
        <div class="card-body">
          <h5 class="card-title">{this.props.name}</h5>
          <p class="card-text">{this.props.address}</p>
        </div>
      </div>
    );
  }
}

export default Card;
