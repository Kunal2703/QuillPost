import { Card, CardBody, CardHeader, Container, Input, Label, Form, FormGroup, Button, Row, Col } from 'reactstrap';
import Base from '../components/Base';
const Signup = () => {
    return (
        <Base>
            <Container>
                <Row className="mt-4">
                    <Col sm={{ size: 6, offset: 3}}>
                        <Card color = "dark" inverse>
                            <CardHeader className="text-center">

                                <h3>SignUp Here !!</h3>

                            </CardHeader>
                            <CardBody>
                                {/* Create Form */}

                                <Form>
                                    {/*User Field */}
                                    <FormGroup>
                                        <Label for="username">User Name</Label>
                                        <Input type="text" placeholder='Choose a unique username' id="username" required></Input>
                                    </FormGroup>

                                    {/*First Name Filed*/}
                                    <FormGroup>
                                        <Label for="firstname">Enter Name</Label>
                                        <Input type="text" placeholder='Enter Your First Name' id="firstname" required></Input>
                                    </FormGroup>

                                    {/*Last Name Filed*/}
                                    <FormGroup>
                                        <Label for="lastname">Enter Name</Label>
                                        <Input type="text" placeholder='Enter Your Last Name' id="lastname" required></Input>
                                    </FormGroup>

                                    {/*email Filed*/}
                                    <FormGroup>
                                        <Label for="email">Enter email</Label>
                                        <Input type="email" placeholder='name@example.com' id="email" required></Input>
                                    </FormGroup>

                                    {/*password Filed*/}
                                    <FormGroup>
                                        <Label for="password">Choose a Password</Label>
                                        <Input type="password" placeholder='Choose Your password' id="password" required></Input>
                                    </FormGroup>

                                    <FormGroup>
                                        <Label for="password">Confirm Password</Label>
                                        <Input type="password" placeholder='Enter your password again' id="password" required></Input>
                                    </FormGroup>

                                    <Container className="text-center" >
                                        <Button outline color='light'  className='mt-4'>
                                            Register
                                        </Button>
                                        <Button color='secondary' type='reset' className='ms-3 mt-4'>
                                            Reset
                                        </Button>
                                    </Container>

                                </Form>

                            </CardBody>
                        </Card>
                    </Col>
                </Row>
            </Container>
        </Base>
    );
};

export default Signup;