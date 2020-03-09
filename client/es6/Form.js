class Form extends React.Component {
    constructor(props) {
        super(props);
        this.state = {value: this.props.currModule.name};
        console.log("Called constructor for form.");
        this.handleNameChange = this.handleNameChange.bind(this);
    }

    handleNameChange(e) {
        this.setState({value: e.target.value});
    }

    render() {
        return e(
            "div",
            {className: "form-container"},
            e(
                "label",
                {className: "form-label"},
                "Module Name:"
            ),
            e(
                "input",
                {
                    value: this.state.value,
                    type: "text",
                    onChange: this.handleNameChange
                }
            )
        );
    }
}

// class _Form extends React.Component {
//     render() {
//         return e(
//             "div",
//             {className: "form-container"},
//             e(NameField, {name: this.props.currModule.name})
//         );
//     }
// }

// class NameField extends React.Component {
//     render() {
//         return e(
//             "div",
//             {className: "form-field"},
//             e(Label, {txt: "Module Name:"}),
//             e(Input, {initValue: this.props.name})
//         );
//     }
// }

// class Label extends React.Component {
//     render() {
//         return e(
//             "label",
//             {className: "form-label"},
//             this.props.txt
//         );
//     }
// }

// class Input extends React.Component {
//     constructor(props) {
//         super(props);
//         this.state = {value: props.initValue};
//         this.handleChange = this.handleChange.bind(this);
//     }

//     handleChange(e) {
//         this.setState({value: e.target.value});
//     }

//     setValue(val) {
//         this.setState({value: val});
//     }

//     render() {
//         return e(
//             "input",
//             {
//                 type: "text",
//                 value: this.state.value,
//                 onChange: this.handleChange
//             }
//         );
//     }
// }
