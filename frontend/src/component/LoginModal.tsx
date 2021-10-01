import React, {useState} from 'react';
import {Button, Modal, ModalHeader, ModalBody, ModalFooter, Label, FormGroup} from 'reactstrap';
import {InputGroup, InputGroupText, InputGroupAddon, Input} from 'reactstrap';
import LoginApi from "../apis/LoginApi";

export default function LoginModal(props: any) {
    const {
        buttonLabel,
        className
    } = props;

    const [modal, setModal] = useState(false);
    const [id, setId] = useState("");
    const [pw, setPw] = useState("");

    const toggle = () => {
        setId("");
        setPw("");
        setModal(!modal)
    };

    async function login() {
        toggle();
        console.log(id, pw)
        let token = await LoginApi.login({userID:id,password:pw,autologin:"1"});
        console.log(token);

    }

    function idChange(e: any) {
        // console.log(e.target.value)
        setId(e.target.value);
    }

    function pwChange(e: any) {
        // console.log(e.target.value)
        setPw(e.target.value);
    }

    return (
        <div>
            <Button color="inherit" onClick={toggle}>{buttonLabel}</Button>
            <Modal isOpen={modal} toggle={toggle} className={className}>
                <ModalHeader toggle={toggle}>Login</ModalHeader>
                <ModalBody>
                    <Input placeholder="ID" value={id} id="id_input" className="mb-2" onChange={idChange}/>
                    <Input placeholder="Password" value={pw}  id="pw_input" onChange={pwChange} type="password"/>
                </ModalBody>
                <ModalFooter>
                    <Button color="primary" onClick={login}>Login</Button>{' '}
                    <Button color="secondary" onClick={toggle}>Cancel</Button>
                </ModalFooter>
            </Modal>
        </div>
    );
}
