<template>
  <div id="blocker">

			<div id="instructions">
				<div class="intro">
					<span style="font-size:36px">전시회를 구경하세요!</span>
					<br /><br />
					움직임: WASD<br/>
					보기: 마우스 이동
				</div>
				
        
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

const objects = [];

let raycaster;

let moveForward = false;
let moveBackward = false;
let moveLeft = false;
let moveRight = false;
let canJump = false;

let prevTime = performance.now();
const velocity = new THREE.Vector3();
const direction = new THREE.Vector3();
// const vertex = new THREE.Vector3();
// const color = new THREE.Color();

function animate() {

  requestAnimationFrame( animate );

  const time = performance.now();

  if ( controls.isLocked === true ) {

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

    controls.getObject().position.y += ( velocity.y * delta ); // new behavior

    if ( controls.getObject().position.y < 10 ) {

      velocity.y = 0;
      controls.getObject().position.y = 10;

      canJump = true;

    }

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


				// 빛
				const light = new THREE.AmbientLight( 0xFFFFFF ); // soft white light
				scene.add( light );


				
				
				//

				controls = new PointerLockControls( camera, document.body );

				const blocker = document.getElementById( 'blocker' );
				const instructions = document.getElementById( 'instructions' );

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
				
				// 유저 움직임

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
          console.log(new THREE.MeshLambertMaterial({map:loader.load(`${imgUrl}`)}));
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
  mounted() {
    init();
    animate();
  },
  
    
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

</style>