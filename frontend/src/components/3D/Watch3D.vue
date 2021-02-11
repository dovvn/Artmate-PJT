<template>
	<div class="wholeScene">

	
		<div id="blocker">
				<div id="instructions">
					<div class="intro" v-if="!isMobile()">
						<span style="font-size:36px">전시회를 구경하세요!</span>
						<br /><br />
						움직임: WASD<br/>
						시점 변화: 마우스 이동
					</div>
					<div class="intro" v-else>
						<span style="font-size:36px">전시회를 구경하세요!</span>
						<br /><br />
						움직임: 왼쪽 하단 컨트롤러<br/>
						시점 변화: 오른쪽 하단 컨트롤러
					</div>
					
				</div>
		</div>
		
		<button class="Exit__button" @click="goBack">
			나가기
		</button>
		<div class="movePad" v-if="isMobile()">
			<font-awesome-icon icon="walking" class="person__icon"/>
			<button class="up__button button">
				<font-awesome-icon icon="angle-up" class="pad__icon"/>
			</button>
			<button class="down__button button">
				<font-awesome-icon icon="angle-down" class="pad__icon"/>
			</button>
			<button class="left__button button">
				<font-awesome-icon icon="angle-left" class="pad__icon"/>
			</button>
			<button class="right__button button">
				<font-awesome-icon icon="angle-right" class="pad__icon"/>
			</button>
			
		</div>
		<div class="cameraPad" v-if="isMobile()">
			<font-awesome-icon icon="camera" class="camera__icon"/>
			<button class="cameraLeft__button button">
				<font-awesome-icon icon="angle-left" class="pad__icon"/>
			</button>
			<button class="cameraRight__button button">
				<font-awesome-icon icon="angle-right" class="pad__icon"/>
			</button>
			<button class="cameraUp__button button">
				<font-awesome-icon icon="angle-up" class="pad__icon"/>
			</button>
			<button class="cameraDown__button button">
				<font-awesome-icon icon="angle-down" class="pad__icon"/>
			</button>
		</div>
	</div>
</template>

<script>
import { PointerLockControls } from './PointerLockControls.js';
import * as THREE from './three.module.js';

const loader = new THREE.TextureLoader();

//테마
// const concreteWhite = new THREE.MeshLambertMaterial({
//       map:loader.load('@/assets/test3D/whiteConcrete.jpg',     
// });

const concreteWhite = new THREE.MeshLambertMaterial({
      map:loader.load('test3D/whiteConcrete.jpg'),
});

const woods = new THREE.MeshLambertMaterial({
      map:loader.load('test3D/wood.jpg'),
});

//

//박스

//색깔 별
const white = new THREE.MeshLambertMaterial(
  {color: 0xffffff,}
);

//box 정해줌
const box = white;

//

let camera, scene, renderer, controls;
let vec = new THREE.Vector3();
let euler = new THREE.Euler(0,0,0,'YXZ');
const objects = [];

let raycaster;

let moveForward = false;
let moveBackward = false;
let moveLeft = false;
let moveRight = false;
let moveCameraRight = false;
let moveCameraLeft = false;
let moveCameraUp = false;
let moveCameraDown = false;
let prev_touch_x = 0;
let prev_touch_y = 0;
let canJump = false;

let prevTime = performance.now();
const velocity = new THREE.Vector3();
const direction = new THREE.Vector3();
// const vertex = new THREE.Vector3();
// const color = new THREE.Color();



function mobileMoveForward(distance) {
	vec.setFromMatrixColumn( camera.matrix, 0 );
	vec.crossVectors( camera.up, vec );
	camera.position.addScaledVector( vec, distance );
}

function mobileMoveRight(distance) {
	vec.setFromMatrixColumn( camera.matrix, 0 );
	camera.position.addScaledVector( vec, distance );
}

function mobileMoveCameraRight() {
	euler.setFromQuaternion(camera.quaternion);
	if(moveCameraRight) {
		euler.y -= moveCameraRight * 0.006;
	}
	if(moveCameraLeft) {
		euler.y += moveCameraLeft * 0.006;
	}
	euler.x = Math.max( Math.PI/2 - Math.PI, Math.min( Math.PI/2 - 0, euler.x ) );

	camera.quaternion.setFromEuler(euler);
}

function mobileMoveCameraUp() {
	euler.setFromQuaternion(camera.quaternion);
	if(moveCameraUp) {
		euler.x += moveCameraUp * 0.006;
	}
	if(moveCameraDown) {
		euler.x -= moveCameraDown * 0.006;
	}
	euler.x = Math.max( Math.PI/2 - Math.PI, Math.min( Math.PI/2 - 0, euler.x ) );

	camera.quaternion.setFromEuler(euler);
}

function animate() {

  requestAnimationFrame( animate );

  const time = performance.now();
	if(screen.availWidth > 900) {
		if ( controls.isLocked === true) {
			
			raycaster.ray.origin.copy( controls.getObject().position );
			raycaster.ray.origin.y -= 10;

			const intersections = raycaster.intersectObjects( objects );

			const onObject = intersections.length > 0;

			const delta = ( time - prevTime ) / 1000;

			velocity.x -= velocity.x * 10.0 * delta;
			velocity.z -= velocity.z * 10.0 * delta;

			velocity.y -= 9.8 * 100.0 * delta; // 100.0 = mass

			direction.z = Number( moveForward ) - Number( moveBackward );
			direction.x = Number( moveRight ) - Number( moveLeft );
			direction.normalize(); // this ensures consistent movements in all directions

			if ( moveForward || moveBackward ) velocity.z -= direction.z * 400.0 * delta;
			if ( moveLeft || moveRight ) velocity.x -= direction.x * 400.0 * delta;

			if ( onObject === true ) {

				velocity.y = Math.max( 0, velocity.y );
				canJump = true;

			}

			controls.moveRight( - velocity.x * delta );
			controls.moveForward( - velocity.z * delta );

		}
	} 
	else {
		const delta = ( time - prevTime ) / 1000;
		velocity.x -= velocity.x * 10.0 * delta;
		velocity.z -= velocity.z * 10.0 * delta;

		velocity.y -= 9.8 * 100.0 * delta; // 100.0 = mass

		direction.z = Number( moveForward ) - Number( moveBackward );
		direction.x = Number( moveRight ) - Number( moveLeft );
		direction.normalize(); // this ensures consistent movements in all directions

		if ( moveForward || moveBackward ) velocity.z -= direction.z * 400.0 * delta;
		if ( moveLeft || moveRight ) velocity.x -= direction.x * 400.0 * delta;	
		mobileMoveRight(- velocity.x * delta);
		mobileMoveForward(- velocity.z * delta);
		mobileMoveCameraRight();
		mobileMoveCameraUp();
	}
  prevTime = time;

  renderer.render( scene, camera );

}
function onWindowResize() {

    camera.aspect = window.innerWidth / window.innerHeight;
    camera.updateProjectionMatrix();

    renderer.setSize( window.innerWidth, window.innerHeight );

  }
function init() {
      	camera = new THREE.PerspectiveCamera( 75, window.innerWidth / window.innerHeight, 1, 1000 );
				camera.position.y = 10;

				scene = new THREE.Scene();
				// scene.background = new THREE.Color( 0xffffff );
				// scene.fog = new THREE.Fog( 0xffffff, 0, 750 );

				const blocker = document.getElementById( 'blocker' );
				const instructions = document.getElementById( 'instructions' );
				const movePad = document.querySelector(".movePad")
				const cameraPad = document.querySelector(".cameraPad")
				
				const upButton = document.querySelector(".up__button");
				const downButton = document.querySelector(".down__button");
				const leftButton = document.querySelector(".left__button");
				const rightButton = document.querySelector(".right__button");
				const cameraRightButton = document.querySelector(".cameraRight__button");
				const cameraLeftButton = document.querySelector(".cameraLeft__button");
				const cameraUpButton = document.querySelector(".cameraUp__button");
				const cameraDownButton = document.querySelector(".cameraDown__button");
				
				// 빛
				const light = new THREE.AmbientLight( 0xFFFFFF ,0.9); // soft white light
				scene.add( light );


				
				
				//

				//PC 일 때만 pointerlock에 연결 카메라 컨트롤
				
					if(screen.availWidth > 900) {
						controls = new PointerLockControls( camera, document.body );
						instructions.addEventListener( 'click', function () {
							controls.lock();
							
						} );
						
						controls.addEventListener( 'lock', function () {

							instructions.style.display = 'none';
							blocker.style.display = 'none';

						} );

						controls.addEventListener( 'unlock', function () {

							blocker.style.display = 'block';
							instructions.style.display = '';

						} );

						scene.add( controls.getObject() );
					} else {
						instructions.addEventListener('touchstart', function() {
							instructions.style.display = 'none';
							// console.log('왜안사라짐');
							blocker.style.display = 'none';
						})
					}
				// 유저 움직임

				//모바일
				if(screen.availWidth < 900) {
					upButton.addEventListener("touchstart", () => {
						moveForward = true;
					})
					downButton.addEventListener("touchstart", () => {
						moveBackward = true;
					})
					leftButton.addEventListener("touchstart", () => {
						moveLeft = true;
					})
					rightButton.addEventListener("touchstart", () => {
						moveRight = true;
					})

					upButton.addEventListener("touchend", () => {
						moveForward = false;
					})
					downButton.addEventListener("touchend", () => {
						moveBackward = false;
					})
					leftButton.addEventListener("touchend", () => {
						moveLeft = false;
					})
					rightButton.addEventListener("touchend", () => {
						moveRight = false;
					})
					cameraLeftButton.addEventListener("touchstart", () => {
						moveCameraLeft = true;
					})
					cameraRightButton.addEventListener("touchstart", () => {
						moveCameraRight = true;
					})
					cameraLeftButton.addEventListener("touchend", () => {
						moveCameraLeft = false;
					})
					cameraRightButton.addEventListener("touchend", () => {
						moveCameraRight = false;
					})
					cameraUpButton.addEventListener("touchstart", () => {
						moveCameraUp = true;
					})
					cameraDownButton.addEventListener("touchstart", () => {
						moveCameraDown = true;
					})
					cameraUpButton.addEventListener("touchend", () => {
						moveCameraUp = false;
					})
					cameraDownButton.addEventListener("touchend", () => {
						moveCameraDown = false;
					})
				}
				//pc
				const onKeyDown = function ( event ) {

					switch ( event.code ) {

						case 'ArrowUp':
						case 'KeyW':
							moveForward = true;
							break;

						case 'ArrowLeft':
						case 'KeyA':
							moveLeft = true;
							break;

						case 'ArrowDown':
						case 'KeyS':
							moveBackward = true;
							break;

						case 'ArrowRight':
						case 'KeyD':
							moveRight = true;
							break;

						// case 'Space':
						// 	if ( canJump === true ) velocity.y += 350;
						// 	canJump = false;
						// 	break;

					}

				};

				const onKeyUp = function ( event ) {

					switch ( event.code ) {

						case 'ArrowUp':
						case 'KeyW':
							moveForward = false;
							break;

						case 'ArrowLeft':
						case 'KeyA':
							moveLeft = false;
							break;

						case 'ArrowDown':
						case 'KeyS':
							moveBackward = false;
							break;

						case 'ArrowRight':
						case 'KeyD':
							moveRight = false;
							break;

					}

				};

				document.addEventListener( 'keydown', onKeyDown );
				document.addEventListener( 'keyup', onKeyUp );

				raycaster = new THREE.Raycaster( new THREE.Vector3(), new THREE.Vector3( 0, - 1, 0 ), 0, 10 );

				// floor

				let floorGeometry = new THREE.PlaneGeometry( 200, 200, 100, 100 );
				floorGeometry.rotateX( - Math.PI / 2 );

				// 바닥 건설

				floorGeometry = floorGeometry.toNonIndexed(); // ensure each face has unique vertices

				const floor = new THREE.Mesh( floorGeometry, concreteWhite );
				scene.add( floor );

				
				//피드 이미지 담을거 만들기
				const imgUrls = ['test3D/feed_1.png','test3D/feed_2.png','test3D/feed_3.png',
				'test3D/feed_4.png','test3D/feed_5.png','test3D/feed_6.png',
				'test3D/feed_7.png','test3D/feed_8.png','test3D/feed_9.png','test3D/feed34.JFIF',]
			
        let imgCnt = 0;
				const imgContainer_left = (imgUrl) => { 
          // console.log(loader.load(`${imgUrl}`))
          return [
					woods,
					woods,
					woods,
					woods,
					woods,
					new THREE.MeshLambertMaterial({map:loader.load(`${imgUrl}`)}),
				]; };

				const imgContainer_right = (imgUrl) => { return [
					woods,
					woods,
					woods,
					woods,
					new THREE.MeshLambertMaterial({map:loader.load(`${imgUrl}`)}),
					woods,
				]; };

				// const color1 = new THREE.Color('skyblue');
				// 천장 건설
				const ceilGeometry = new THREE.BoxGeometry( 200, 20, 200 ).toNonIndexed();
				const ceil = new THREE.Mesh( ceilGeometry, concreteWhite );
				ceil.position.x = 0;
				ceil.position.y = 60;
				ceil.position.z = 0;
				scene.add(ceil);
				// 박스 건설
				const boxGeometry = new THREE.BoxGeometry( 10, 20, 10 ).toNonIndexed();
				
				// 액자 건설
				const ekjaGeometry = new THREE.BoxGeometry(12,22,12).toNonIndexed();

				for(let x = -100; x <= 100; x += 10){
					const box11 = new THREE.Mesh( boxGeometry, box );
					box11.position.x = x;
					box11.position.y = 0;
					box11.position.z = -100;
					
					let mg,mt;
					if(x % 40 == 0) {
						mt = imgContainer_right(imgUrls[imgCnt++]);
						mg = ekjaGeometry;
					} else {
						mt = box;
						mg = boxGeometry;
					}
					let box12 = new THREE.Mesh( mg, mt );
				
					box12.position.x = x;
					box12.position.y = 20;
					box12.position.z = -100;
					if(x%40==0) {
						let spotlight = new THREE.SpotLight(0xffffff,0.3);
						spotlight.position.x = x;
						spotlight.position.y = 20;
						spotlight.position.z = -88;
						spotlight.castShadow = true;
						scene.add(spotlight);
						scene.add(spotlight.target);
						spotlight.target.position.x = x;
						spotlight.target.position.y = 20;
						spotlight.target.position.z = -100;
					}
					const box13 = new THREE.Mesh( boxGeometry, box );
					box13.position.x = x;
					box13.position.y = 40;
					box13.position.z = -100;
					const box21 = new THREE.Mesh( boxGeometry, box );
					box21.position.x = x;
					box21.position.y = 0;
					box21.position.z = 100;
					
					if(x % 40 == 0) {
						mt = imgContainer_left(imgUrls[imgCnt++]);
						mg = ekjaGeometry;
					} else {
						mt = box;
						mg = boxGeometry;
					}
					let box22 = new THREE.Mesh( mg, mt );
					box22.position.x = x;
					box22.position.y = 20;
					box22.position.z = 100;
						if(x%40==0) {
						let spotlight = new THREE.SpotLight(0xffffff,0.3);
						spotlight.position.x = x;
						spotlight.position.y = 20;
						spotlight.position.z = 85;
						spotlight.castShadow = true;
						scene.add(spotlight);
						scene.add(spotlight.target);
						spotlight.target = box22;
						// spotlight.target.position.x = x;
						// spotlight.target.position.y = 20;
						// spotlight.target.position.z = 100;
					}
					const box23 = new THREE.Mesh( boxGeometry, box );
					box23.position.x = x;
					box23.position.y = 40;
					box23.position.z = 100;
					scene.add( box11,box12,box13,box21,box22,box23 );
					objects.push( box11,box12,box13,box21,box22,box23 );
				}
				
				for(let z = -90; z <= 90; z += 10){
					//3층으로 쌓자.
					const box11 = new THREE.Mesh( boxGeometry, box );
					box11.position.x = -100;
					box11.position.y = 0;
					box11.position.z = z;
					const box12 = new THREE.Mesh( boxGeometry, box );
					box12.position.x = -100;
					box12.position.y = 20;
					box12.position.z = z;
					const box13 = new THREE.Mesh( boxGeometry, box );
					box13.position.x = -100;
					box13.position.y = 40;
					box13.position.z = z;
					const box21 = new THREE.Mesh( boxGeometry, box );
					box21.position.x = 100;
					box21.position.y = 0;
					box21.position.z = z;
					const box22 = new THREE.Mesh( boxGeometry, box );
					box22.position.x = 100;
					box22.position.y = 20;
					box22.position.z = z;
					const box23 = new THREE.Mesh( boxGeometry, box );
					box23.position.x = 100;
					box23.position.y = 40;
					box23.position.z = z;
					scene.add( box11,box12,box13,box21,box22,box23 );
					objects.push( box11,box12,box13,box21,box22,box23 );
				}
				

				renderer = new THREE.WebGLRenderer( { antialias: true } );
				renderer.setPixelRatio( window.devicePixelRatio );
				renderer.setSize( window.innerWidth, window.innerHeight );
				document.body.appendChild( renderer.domElement );


				window.addEventListener( 'resize', onWindowResize );
    }
export default {
	created() {
		// 해당 유저의 전시회피드들을 가져온다.
		// 그 다음 걔네를 imgurl => feeds에 담는다.
		// init()에 인자로 준다음에 돌린다.
	},
  mounted() {
    init();
    animate();
  },
  methods: {
		goBack() {
			//해당 유저피드로 이동
			console.log('뒤로가!');
		},
		isMobile() {
			// console.log(screen.availWidth);
			return screen.availWidth < 900;
		}
	}
    
}
</script>

<style scoped>
#blocker {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: rgba(0,0,0,0.5);
}

#instructions {
  width: 100%;
  height: 100%;

  display: -webkit-box;
  display: -moz-box;
  /* display: box; */

  -webkit-box-orient: horizontal;
  -moz-box-orient: horizontal;
  box-orient: horizontal;

  -webkit-box-pack: center;
  -moz-box-pack: center;
  box-pack: center;

  -webkit-box-align: center;
  -moz-box-align: center;
  box-align: center;

  color: #ffffff;
  text-align: center;
  font-family: Arial;
  font-size: 14px;
  line-height: 24px;

  cursor: pointer;
	display:flex;
}

.intro {
	width:100%;
	/* height:100%; */
	/* display:flex; */
	align-self:center;
}

body {
	margin: 0;
	background-color: #000;
	color: #fff;
	font-family: Monospace;
	font-size: 13px;
	line-height: 24px;
	overscroll-behavior: none;
}

a {
	color: #ff0;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

button {
	cursor: pointer;
	text-transform: uppercase;
}

#info {
	position: absolute;
	top: 0px;
	width: 100%;
	padding: 10px;
	box-sizing: border-box;
	text-align: center;
	-moz-user-select: none;
	-webkit-user-select: none;
	-ms-user-select: none;
	user-select: none;
	pointer-events: none;
	z-index: 1; /* TODO Solve this in HTML */
}

a, button, input, select {
	pointer-events: auto;
}

.dg.ac {
	-moz-user-select: none;
	-webkit-user-select: none;
	-ms-user-select: none;
	user-select: none;
	z-index: 2 !important; /* TODO Solve this in HTML */
}

#overlay {
	position: absolute;
	font-size: 16px;
	z-index: 2;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
	background: rgba(0,0,0,0.7);
}

	#overlay button {
		background: transparent;
		border: 0;
		border: 1px solid rgb(255, 255, 255);
		border-radius: 4px;
		color: #ffffff;
		padding: 12px 18px;
		text-transform: uppercase;
		cursor: pointer;
	}

#notSupported {
	width: 50%;
	margin: auto;
	background-color: #f00;
	margin-top: 20px;
	padding: 10px;
}

.Exit__button {
	position:absolute;
	right:20px;
	top:20px;
	border-radius:15px;
	background: linear-gradient(270deg,#A593DF,#9279E9,#7D5BF4,#6D44FD );
	color: white;
	font-size:12px;
	padding:5px;
}

.movePad {
	position:fixed;
	bottom:20px;
	left:20px;
	width:100px;
	height:100px;
	/* opacity:0.2; */
}

.cameraPad {
	position:fixed;
	bottom:20px;
	right:30px;
	width:100px;
	height:100px;
}

.person__icon {
	position:absolute;
	width:30px;
	color:white;
	opacity:0.6;
	height:30px;
	left:42px;
	bottom:40px;
}

.camera__icon {
	position:absolute;
	width:25px;
	color:white;
	opacity:0.6;
	height:25px;
	left:45px;
	bottom:40px;
}

.button {
	position:absolute;
	font-size:60px;
	width:45px;
	height:45px;
	/* background-color: black; */
	color:white;
	opacity: 0.6;
	text-align:center;
	line-height:45px;
}

.button:active {
	opacity:1;
}

.up__button,
.cameraUp__button {
	left:35px;
	bottom:75px;
}

.down__button,
.cameraDown__button {
	left:35px;
	bottom:0px;	
}

.left__button,
.cameraLeft__button {
	left:0px;
	bottom:35px;
}

.right__button,
.cameraRight__button {
	left:70px;
	bottom:35px;
}

</style>