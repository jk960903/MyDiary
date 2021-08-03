import React from 'react';
import {
    Navbar,
    NavbarBrand,
    Nav,
    UncontrolledDropdown,
    DropdownToggle,
    DropdownMenu,
    DropdownItem,
} from 'reactstrap';
import LoginModal from './LoginModal'

export default class Header extends React.Component{
    render(){
        return (
            <div>
                <Navbar color="light" light expand="md">
                    <NavbarBrand href="/" className="ms-4">Title</NavbarBrand>
                    <Nav className="ms-2" navbar>
                        <UncontrolledDropdown nav inNavbar>
                            <DropdownToggle nav>
                                Options
                            </DropdownToggle>
                            <DropdownMenu right>
                                <DropdownItem href="/sub">
                                    Option 1
                                </DropdownItem>
                                <DropdownItem>
                                    Option 2
                                </DropdownItem>
                            </DropdownMenu>
                        </UncontrolledDropdown>
                    </Nav>
                    <Nav className="ms-auto me-4">
                        <LoginModal/>
                    </Nav>
                </Navbar>
            </div>
        );
    }
}