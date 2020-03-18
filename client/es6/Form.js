class Form extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            nameValue: props.currModule.name,
            defValue: props.currModule.definition,
            usesValues: [],
            testValues: []
        };
        for (let i = 0; i < props.currModule.uses.length; i++) {
            this.state.usesValues[i] = props.currModule.uses[i];
        }
        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleDefinitionChange = this.handleDefinitionChange.bind(this);
        this.handleUsesChange = this.handleUsesChange.bind(this);
        this.addUses = this.addUses.bind(this);
        this.removeUses = this.removeUses.bind(this);
        this.addTest = this.addTest.bind(this);
        this.removeTest = this.removeTest.bind(this);
        this.updateTestValues = this.updateTestValues.bind(this);
    }

    handleNameChange(e) {
        this.setState({nameValue: e.target.value});
        this.props.change(this.props.index, "name", e.target.value);
    }

    handleDefinitionChange(e) {
        this.setState({defValue: e.target.value});
        this.props.change(this.props.index, "definition", e.target.value);
    }

    handleUsesChange(e) {
        let newUsesValues = [];
        for (let i = 0; i < this.state.usesValues.length; i++) {
            if (i === parseInt(e.target.id, 10)) {
                newUsesValues[i] = e.target.value;
            }
            else {
                newUsesValues[i] = this.state.usesValues[i]; 
            }
        }
        this.setState({
            usesValues: newUsesValues
        });
        this.props.change(this.props.index, "uses", newUsesValues);
    }

    addUses() {
        this.setState((state, props) => {
            let newUsesValues = [...state.usesValues, ""];
            props.change(props.index, "uses", newUsesValues);
            return {
                usesValues: newUsesValues
            };
        });
    }

    removeUses(index) {
        this.setState((state, props) => {
            let newUsesValues = [...state.usesValues.slice(0,index), ...state.usesValues.slice(index+1)];
            props.change(props.index, "uses", newUsesValues);
            return {
                usesValues: newUsesValues
            };
        });
    }

    addTest() {
        this.setState((state, props) => {
            let newTestValues = [...state.testValues, ""];
            return {
                testValues: newTestValues
            };
        });
    }

    removeTest(index) {
        this.setState((state, props) => {
            let newTestValues = [...state.testValues.slice(0,index), ...state.testValues.slice(index+1)];
            return {
                testValues: newTestValues
            };
        });
    }

    updateTestValues(values) {
        this.setState((state, props) => {
            return {
                testValues: values
            };
        });
    }

    render() {
        let extraUses = [];
        for (let i = 1; i < this.state.usesValues.length; i++) {
            extraUses[i-1] = e(
                "tr",
                {
                    key: i,
                    
                },
                e(
                    "td",
                    {},
                    ""
                ),
                e(
                    "td",
                    {},
                    e(
                        "input",
                        {
                            type: "text",
                            id: i,
                            value: this.state.usesValues[i],
                            onChange: this.handleUsesChange
                        }
                    )
                ),
                e(
                    "td",
                    {},
                    e(
                        RemoveButton,
                        {
                            click: () => this.removeUses(i)
                        }
                    )
                )
            );
        }
        return e(
            "div",
            {className: "form-container"},
            e(
                "table",
                {},
                e(
                    "tbody",
                    {},
                    e(
                        "tr",
                        {},
                        e(
                            "td",
                            {},
                            e(
                                "label",
                                {className: "form-label"},
                                "Module Name:"
                            )
                        ),
                        e(
                            "td",
                            {},
                            e(
                                "input",
                                {
                                    type: "text",
                                    value: this.state.nameValue,
                                    onChange: this.handleNameChange
                                }
                            )
                        )
                    ),
                    e(
                        "tr",
                        {},
                        e(
                            "td",
                            {},
                            e(
                                "label",
                                {className: "form-label"},
                                "Definition:"
                            )
                        ),
                        e(
                            "td",
                            {},
                            e(
                                "input",
                                {
                                    type: "text",
                                    value: this.state.defValue,
                                    onChange: this.handleDefinitionChange
                                }
                            )
                        )
                    ),
                    e(
                        "tr",
                        {},
                        e(
                            "td",
                            {},
                            e(
                                "label",
                                {className: "form-label"},
                                "Uses:"
                            )
                        ),
                        e(
                            "td",
                            {},
                            (this.state.usesValues.length === 0) ?
                                e(
                                    NewButton,
                                    {
                                        click: () => this.addUses()
                                    }
                                )
                            :
                                e(
                                    "input",
                                    {
                                        type: "text",
                                        id: 0,
                                        value: this.state.usesValues[0],
                                        onChange: this.handleUsesChange
                                    }
                                )
                        ),
                        (this.state.usesValues.length > 0) ?
                            e(
                                "td",
                                {},
                                e(
                                    RemoveButton,
                                    {
                                        click: () => this.removeUses(0)
                                    }
                                )
                            )
                        :
                            null
                    ),
                    extraUses,
                    (this.state.usesValues.length > 0) ?
                        e(
                            "tr",
                            {},
                            e(
                                "td",
                                {},
                                ""
                            ),
                            e(
                                "td",
                                {},
                                e(
                                    NewButton,
                                    {
                                        click: () => this.addUses()
                                    }
                                )
                            )
                        )
                    :
                        null
                    ,
                    e(
                        "tr",
                        {},
                        e(
                            "td",
                            {colSpan: "2"},
                            e(
                                "h2",
                                {className: "subtitle"},
                                "Syntax"
                            )
                        )
                    ),
                    e(
                        "tr",
                        {},
                        e(
                            "td",
                            {},
                            e(
                                "label",
                                {className: "form-label"},
                                "Exported Types:"
                            )
                        ),
                        e(
                            "td",
                            {},
                            e(
                                "input",
                                {
                                    type: "text",
                                    size: "18"
                                }
                            ),
                            " = "
                        ),
                        e(
                            "td",
                            {},
                            e(
                                "textarea",
                                {}
                            )
                        )
                    ),
                    e(
                        "tr",
                        {},
                        e(
                            "td",
                            {},
                            e(
                                "label",
                                {className: "form-label"},
                                "Access Programs:"
                            )
                        ),
                        e(
                            "td",
                            {},
                            e(NewButton)
                        )
                    ),
                    e(
                        "tr",
                        {},
                        e(
                            "td",
                            {colSpan: "2"},
                            e(
                                "h2",
                                {className: "subtitle"},
                                "Semantics"
                            )
                        )
                    ),
                    e(
                        "tr",
                        {},
                        e(
                            "td",
                            {},
                            e(
                                "label",
                                {className: "form-label"},
                                "State Variables:"
                            )
                        ),
                        e(
                            "td",
                            {},
                            e(
                                NewButton
                            )
                        )
                    ),
                    e(
                        "tr",
                        {},
                        e(
                            "td",
                            {},
                            e(
                                "label",
                                {className: "form-label"},
                                "State Invariants:"
                            )
                        ),
                        e(
                            "td",
                            {},
                            e(
                                NewButton
                            )
                        )
                    ),
                    e(
                        "tr",
                        {},
                        e(
                            "td",
                            {},
                            e(
                                "label",
                                {className: "form-label"},
                                "Assumptions:"
                            )
                        ),
                        e(
                            "td",
                            {},
                            e(
                                "textarea",
                                {}
                            )
                        )
                    ),
                    e(
                        Expandable,
                        {
                            label: "TestList",
                            update: this.updateTestValues,
                            addEntry: this.addTest,
                            entries: []
                        }
                    )
                )
            )
        );
    }
}

/**
 * A form component (table row) with two columns. The left column is the label. Right column is an "Add Input"
 * button. Pressing the button will create a new row, move the button to the second column of the new row, and
 * put in its place a text input box. Indefinitely many new input boxes can be created.
 * 
 * @prop entries An array of strings representing entry values.
 * @prop label A string label for the field.
 * @prop addEntry A function expecting no arguments. 
 */
class Expandable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            entries: []
        };
        for (let i = 0; i < props.entries.length; i++) {
            this.state.entries[i] = props.entries[i];
        }
        this.addEntry = this.addEntry.bind(this);
        this.removeEntry = this.removeEntry.bind(this);
    }

    // handleChange(e) {
    //     this.state
    // }

    addEntry() {
        this.setState((state, props) => {
            let newEntries = [...state.entries, ""];
            props.update(newEntries);
            return {
                entries: newEntries
            };
        });
    }

    removeEntry(index) {
        this.setState((state, props) => {
            let newEntries = [...state.entries.slice(0,index), ...state.entries.slice(index+1)];
            props.update(newEntries);
            return {
                entries: newEntries
            };
        });
    }    
    
    render() {
        return e(
            "tr",
            {},
            e(
                "td",
                {},
                e(
                    "label",
                    {className: "form-label"},
                    this.props.label
                )
            ),
            e(
                "td",
                {},
                (this.state.entries.length === 0) ?
                    e(
                        NewButton,
                        {
                            click: () => this.props.addEntry()
                        }
                    )
                :
                    e(
                        "input",
                        {
                            type: "text",
                            id: 0
                        }
                    )
            )
        );
    }
}
