class NewButton extends React.Component {
    render() {
        return e("button", {className: "new-button", onClick: this.props.click}, "+");
    }
}

class RemoveButton extends React.Component {
    render() {
        return e("button", {className: "new-button", onClick: this.props.click}, "-");
    }
}
